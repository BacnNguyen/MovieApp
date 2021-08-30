package gst.trainingcourse.movie.ui.fragment.movie_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.Comment
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.databinding.FragmentDetailBinding
import gst.trainingcourse.movie.helper.LinearHorizontalItemDecoration
import gst.trainingcourse.movie.helper.LinearVerticalItemDecoration
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.movie_detail.adapter.CommentAdapter
import gst.trainingcourse.movie.ui.fragment.movie_detail.adapter.RecommendAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@SuppressLint("CheckResult")
@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel by viewModels<MovieDetailViewModel>()

    private val recommendAdapter by lazy { RecommendAdapter(this::onMovieClick) }

    @Inject
    lateinit var firebase: FirebaseDatabase

    private val requestOptions by lazy {
        RequestOptions().apply {
            transform(CenterCrop())
        }
    }

    private val commentAdapter by lazy {
        CommentAdapter()
    }

    private val arg by navArgs<MovieDetailFragmentArgs>()

    private val valueEventListener by lazy {
        object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val comments = mutableListOf<Comment>()
                snapshot.children.forEach {
                    it.getValue(Comment::class.java)?.let { it1 -> comments.add(it1) }
                }
                commentAdapter.submitList(comments)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadUI(arg.movieId)

        setupRecyclerViewRecommend()
        setupRecyclerViewComment()

        observeData()

        setListener()
    }

    private fun loadUI(movieId: Int) {
        viewModel.apply {
            getMovieDetail(movieId)
            getMovieRecommend(movieId)
            getTrailer(movieId)
        }

        firebase.reference.child("$movieId").addValueEventListener(valueEventListener)
    }

    private fun observeData() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            binding.apply {
                Glide.with(requireContext())
                    .load("${BuildConfig.BASE_IMAGE_URL}${it.posterPath}")
                    .apply(requestOptions)
                    .into(imagePoster)

                textViewName.text = it.title
                textViewReleaseDate.text = it.releaseDate
                textViewOverview.text = it.overview

                lifecycleScope.launch(Dispatchers.IO) {
                    val movie = viewModel.getMovie(it.id)
                    withContext(Dispatchers.Main) {
                        imageFavorite.isActivated = movie != null
                    }
                }
            }
        }

        viewModel.movieRecommend.observe(viewLifecycleOwner) {
            recommendAdapter.submitList(it)
        }

        viewModel.trailer.observe(viewLifecycleOwner) {
            it.results.firstOrNull()?.key?.let {
                lifecycleScope.launch {
                    getYoutubePlayer(binding.youtubeView).loadVideo(it, 0F)
                }
            }
        }
    }

    private fun setListener() {
        binding.apply {
            buttonComment.setOnClickListener {
                val username = editTextUserName.text?.toString()?.trim() ?: ""
                val comment = editTextComment.text?.toString()?.trim() ?: ""
                if (username.isEmpty() || comment.isEmpty()) return@setOnClickListener
                val currentMovieId = viewModel.movieDetail.value?.id ?: return@setOnClickListener
                firebase.reference.child("$currentMovieId").push()
                    .setValue(Comment(username, comment))
                editTextUserName.text?.clear()
                editTextComment.text?.clear()
            }
            imageFavorite.setOnClickListener {
                if (imageFavorite.isActivated) {
                    viewModel.movieDetail.value?.id?.let {
                        viewModel.deleteMovie(it)
                        imageFavorite.isActivated = !imageFavorite.isActivated
                    }
                } else {
                    viewModel.movieDetail.value?.let {
                        viewModel.saveMovie(it)
                        viewModel.saveRecommendMovie()
                        imageFavorite.isActivated = !imageFavorite.isActivated
                    }
                }
            }
        }
    }

    private fun setupRecyclerViewRecommend() {
        binding.recyclerViewRecommend.apply {
            adapter = recommendAdapter
            addItemDecoration(LinearHorizontalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_8)))
        }
    }

    private fun setupRecyclerViewComment() {
        binding.recyclerViewComment.apply {
            adapter = commentAdapter
            addItemDecoration(LinearVerticalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_8)))
        }
    }

    private fun onMovieClick(movie: MovieResponse.Movie) {
        loadUI(movie.id)
    }

    private suspend fun getYoutubePlayer(youTubePlayerView: YouTubePlayerView): YouTubePlayer {
        return suspendCoroutine { continuation ->
            youTubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    continuation.resume(youTubePlayer)
                }
            })
        }
    }

    override fun onDestroyView() {
        lifecycleScope.launch {
            getYoutubePlayer(binding.youtubeView).pause()
        }
        super.onDestroyView()
    }

    companion object {
        const val TAG = "DetailFragment"
        const val ARG_DETAIL_MOVIE = "ARG_DETAIL_MOVIE"

        fun newInstance(movieId: Int) = MovieDetailFragment().apply {
            arguments = Bundle().apply { putInt(ARG_DETAIL_MOVIE, movieId) }
        }
    }

}