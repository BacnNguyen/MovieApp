package gst.trainingcourse.movie.ui.fragment.category.movie.movieUpComing

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.databinding.FragmentCategoryMovieBinding
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.category.movie.adapter.MovieAdapter

@AndroidEntryPoint
class MovieUpComingFragment :
    BaseFragment<FragmentCategoryMovieBinding>(R.layout.fragment_category_movie) {

    private var isList = true

    private val viewModel by viewModels<MovieUpComingViewModel>()

    private val movieAdapter by lazy { MovieAdapter(this::onMovieClick) }

    private lateinit var listGridImg: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listGridImg = view.findViewById(R.id.list_grid_img)

        listGridImg.setOnClickListener({
            if (isList) {
                listGridImg.setImageResource(R.drawable.ic_baseline_view_list_24)
                isList = false
            } else {
                listGridImg.setImageResource(R.drawable.ic_baseline_grid_on_24)
                isList = true
            }
            setupMovieRecyclerView()
        })

        setupMovieRecyclerView()
        observeData()
    }

    private fun observeData() {
        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }

    private fun setupMovieRecyclerView() {
        if (isList) {
            binding.recylerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                // layoutManager = GridLayoutManager(requireContext(),2)
                adapter = movieAdapter
            }
        } else {
            binding.recylerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = movieAdapter
            }
        }
    }

    private fun onMovieClick(item: MovieResponse.Movie, position: Int) {
        val action = MovieUpComingFragmentDirections.actionMovieUpComingToMovieDetail(item.id)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "MovieUpComingFragment"

        fun newInstance() = MovieUpComingFragment()
    }

}