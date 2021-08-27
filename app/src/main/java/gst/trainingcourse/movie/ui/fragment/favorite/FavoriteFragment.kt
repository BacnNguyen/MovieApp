package gst.trainingcourse.movie.ui.fragment.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.databinding.FragmentFavoriteBinding
import gst.trainingcourse.movie.helper.LinearHorizontalItemDecoration
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.home.adapter.MovieAdapter
import gst.trainingcourse.movie.ui.fragment.home.adapter.TVShowAdapter

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val viewModel by viewModels<FavoriteViewModel>()

    private val movieAdapter by lazy { MovieAdapter(this::onMovieClick) }

    private val tvShowAdapter by lazy { TVShowAdapter(this::onTVShowClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMovieRecyclerView()
        setupTVShowRecyclerView()

        observeData()
    }

    private fun observeData() {
        viewModel.getAllMovie().observe(viewLifecycleOwner) {

            if (it == null || it.isEmpty()) {
                binding.apply {
                    recyclerViewMovie.visibility = View.GONE
                    textViewMovie.visibility = View.GONE
                }
            } else {
                binding.apply {
                    recyclerViewMovie.visibility = View.VISIBLE
                    textViewMovie.visibility = View.VISIBLE
                }
            }

            movieAdapter.submitList(it)
        }

        viewModel.getAllTVShow().observe(viewLifecycleOwner) {
            if (it == null || it.isEmpty()) {
                binding.apply {
                    recyclerViewTvShow.visibility = View.GONE
                    textViewTvShow.visibility = View.GONE
                }
            } else {
                binding.apply {
                    recyclerViewTvShow.visibility = View.VISIBLE
                    textViewTvShow.visibility = View.VISIBLE
                }
            }

            tvShowAdapter.submitList(it)
        }
    }

    private fun setupMovieRecyclerView() {
        binding.recyclerViewMovie.apply {
            adapter = movieAdapter
            addItemDecoration(LinearHorizontalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_8)))
        }
    }

    private fun setupTVShowRecyclerView() {
        binding.recyclerViewTvShow.apply {
            adapter = tvShowAdapter
            addItemDecoration(LinearHorizontalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_8)))
        }
    }

    private fun onMovieClick(item: MovieResponse.Movie, position: Int) {
        val action = FavoriteFragmentDirections.actionFavoriteToMovieDetail(item.id)
        findNavController().navigate(action)
    }

    private fun onTVShowClick(item: TvShowResponse.TvShow, position: Int) {
        val action = FavoriteFragmentDirections.actionFavoriteToTvShowDetail(item.id)
        findNavController().navigate(action)
    }
}