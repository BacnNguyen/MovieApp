package gst.trainingcourse.movie.ui.fragment.category.show.showPopular

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.databinding.FragmentCategoryMovieBinding
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.category.show.adapter.ShowAdapter
import gst.trainingcourse.movie.ui.fragment.category.show.showAiringToday.ShowAiringTodayFragmentDirections

@AndroidEntryPoint
class ShowPopularFragment :
    BaseFragment<FragmentCategoryMovieBinding>(R.layout.fragment_category_movie) {

    private var isList = true

    private val viewModel by viewModels<ShowPopularViewModel>()

    private val movieAdapter by lazy { ShowAdapter(this::onMovieClick) }

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

    private fun onMovieClick(item: TvShowResponse.TvShow, position: Int) {
        val action = ShowPopularFragmentDirections.actionShowPopularToTvShowDetail(item.id)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "ShowPopularFragment"

        fun newInstance() = ShowPopularFragment()
    }

}