package gst.trainingcourse.movie.ui.fragment.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.Category
import gst.trainingcourse.movie.databinding.FragmentCategoryBinding
import gst.trainingcourse.movie.helper.GridVerticalItemDecoration
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.category.adapter.CategoryAdapter

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private val viewModel by viewModels<CategoryViewModel>()

    private val categoryAdapter by lazy { CategoryAdapter(resources, this::onCategoryClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoryRecyclerView()
        observeData()
    }

    private fun setupCategoryRecyclerView() {
        binding.recyclerView.apply {
            adapter = categoryAdapter
            addItemDecoration(GridVerticalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_4)))
        }
    }

    private fun observeData() {
        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }
    }

    private fun onCategoryClick(item: Category, position: Int) {
        val action = when (item.name) {
            "Movie Popular" -> CategoryFragmentDirections.actionCategoryToMoviePopular()
            "Movie Now Playing" -> CategoryFragmentDirections.actionCategoryToMovieNowPlaying()
            "Movie Top Rated" -> CategoryFragmentDirections.actionCategoryToMovieTopRated()
            "Movie Upcoming" -> CategoryFragmentDirections.actionCategoryToMovieUpComing()
            "TV Show Popular" -> CategoryFragmentDirections.actionCategoryToShowPopular()
            "TV Show Top Rated" -> CategoryFragmentDirections.actionCategoryToShowTopRated()
            "TV Show Airing Today" -> CategoryFragmentDirections.actionCategoryToShowAiringToday()
            else -> CategoryFragmentDirections.actionCategoryToShowOnTheAir()
        }
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "CategoryFragment"

        fun newInstance() = CategoryFragment()
    }
}