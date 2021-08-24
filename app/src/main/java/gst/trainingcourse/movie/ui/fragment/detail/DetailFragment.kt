package gst.trainingcourse.movie.ui.fragment.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.databinding.FragmentDetailBinding
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.detail.adapter.MovieCommentAdapter
import gst.trainingcourse.movie.ui.fragment.detail.adapter.MovieRecommendAdapter


@SuppressLint("CheckResult")
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    var adapter_recommend: MovieRecommendAdapter = MovieRecommendAdapter(this::onItemClick)


    private val requestOptions by lazy {
        RequestOptions().apply {
            transform(CenterCrop())
        }
    }

    private fun onItemClick(movie: MovieResponse.Movie) {
        fetchData(movieId = movie.id)
    }

    var adapter_comment: MovieCommentAdapter = MovieCommentAdapter()
    val detailviewmodel by viewModels<DetailViewModel>()

    companion object {
        const val TAG = "DetailFragment"
        const val ARG_DETAIL_MOVIE = "detail.movie"

        fun newInstance(movieId: Int) = DetailFragment().apply {
            arguments = Bundle().apply { putInt(ARG_DETAIL_MOVIE, movieId) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("TAG", "onViewCreated: ====================")
        arguments?.getInt(ARG_DETAIL_MOVIE)?.let {
            fetchData(it)
        }

        setupObserve()
        binding.recycleViewRecommend.adapter = adapter_recommend
        binding.recycleViewComment.adapter = adapter_comment
    }

    fun fetchData(movieId: Int) {
        detailviewmodel.fetchData(movieId = movieId)
    }


    private fun setupObserve() {
        detailviewmodel.movie.observe(viewLifecycleOwner, {
            Glide.with(requireContext())
                .load("${BuildConfig.BASE_IMAGE_URL}${it.posterPath}")
                .apply(requestOptions)
                .into(binding.avtMovie)

            binding.nameMovie.text = it.title
            binding.yearMovie.text = it.releaseDate
            binding.descriptionMovie.text = it.overview
        })

        detailviewmodel.movies_recommend.observe(viewLifecycleOwner, {
            adapter_recommend.submitList(it)
        })

        detailviewmodel.comments.observe(viewLifecycleOwner, {
            adapter_comment.submitList(it)
        })

    }

}