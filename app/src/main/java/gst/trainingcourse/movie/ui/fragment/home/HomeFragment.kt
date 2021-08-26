package gst.trainingcourse.movie.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.Recommend
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.databinding.FragmentHomeBinding
import gst.trainingcourse.movie.helper.LinearHorizontalItemDecoration
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.home.adapter.MovieAdapter
import gst.trainingcourse.movie.ui.fragment.home.adapter.RecommendAdapter
import gst.trainingcourse.movie.ui.fragment.home.adapter.TVShowAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private val movieAdapter by lazy { MovieAdapter(this::onMovieClick) }

    private val tvShowAdapter by lazy { TVShowAdapter(this::onTVShowClick) }

    private val recommendAdapter by lazy { RecommendAdapter(this::onRecommendClick) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMovieRecyclerView()
        setupTVShowRecyclerView()
        setupRecommendRecyclerView()
        observeData()

        setEvent()
    }

    private fun setupRecommendRecyclerView() {
        binding.recyclerViewRecommendForYou.apply {
            adapter = recommendAdapter
            addItemDecoration(LinearHorizontalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_8)))
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

    private fun setEvent() {
        binding.viewSearch.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_search)
        }
    }

    private fun observeData() {
        viewModel.getAllRecommend().observe(viewLifecycleOwner) {
            recommendAdapter.submitList(it)
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }

        viewModel.tvShow.observe(viewLifecycleOwner) {
            tvShowAdapter.submitList(it)
        }
    }

    private fun onMovieClick(item: MovieResponse.Movie, position: Int) {
        val action = HomeFragmentDirections.actionHomeToMovieDetail(item.id)
        findNavController().navigate(action)
    }

    private fun onTVShowClick(item: TvShowResponse.TvShow, position: Int) {
        val action = HomeFragmentDirections.actionHomeToTvShowDetail(item.id)
        findNavController().navigate(action)
    }

    private fun onRecommendClick(item: Recommend) {
        when (item.isMovie) {
            true -> {
                val action = HomeFragmentDirections.actionHomeToMovieDetail(item.id)
                findNavController().navigate(action)
            }
            else -> {
                val action = HomeFragmentDirections.actionHomeToTvShowDetail(item.id)
                findNavController().navigate(action)
            }
        }
    }

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }
}