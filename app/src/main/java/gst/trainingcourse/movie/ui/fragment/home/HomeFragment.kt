package gst.trainingcourse.movie.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.databinding.FragmentHomeBinding
import gst.trainingcourse.movie.helper.LinearHorizontalItemDecoration
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.home.adapter.MovieAdapter
import gst.trainingcourse.movie.ui.fragment.home.adapter.TVShowAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private val movieAdapter by lazy { MovieAdapter(this::onMovieClick) }

    private val tvShowAdapter by lazy { TVShowAdapter(this::onTVShowClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMovieRecyclerView()
        setupTVShowRecyclerView()

        observeData()
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

    private fun observeData() {
        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }

        viewModel.tvShow.observe(viewLifecycleOwner) {
            tvShowAdapter.submitList(it)
        }
    }

    private fun onMovieClick(item: MovieResponse.Movie, position: Int) {

    }

    private fun onTVShowClick(item: TvShowResponse.TvShow, position: Int) {

    }

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }
}