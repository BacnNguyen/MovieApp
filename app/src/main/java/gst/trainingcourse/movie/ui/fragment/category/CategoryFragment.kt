package gst.trainingcourse.movie.ui.fragment.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
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

    }

    companion object {
        const val TAG = "CategoryFragment"

        fun newInstance() = CategoryFragment()
    }
}