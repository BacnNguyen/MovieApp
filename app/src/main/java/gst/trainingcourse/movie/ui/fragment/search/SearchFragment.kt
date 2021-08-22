package gst.trainingcourse.movie.ui.fragment.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.data.model.SearchResponse
import gst.trainingcourse.movie.databinding.FragmentSearchBinding
import gst.trainingcourse.movie.helper.LinearVerticalItemDecoration
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment
import gst.trainingcourse.movie.ui.fragment.search.adapter.SearchAdapter

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()

    private val autoCompleteAdapter by lazy {
        ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )
    }

    private val searchAdapter by lazy { SearchAdapter(this::onSearchResultClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAutoCompleteTextView()
        setupRecyclerViewSearchResult()

        observeData()
    }

    private fun observeData() {
        viewModel.history.observe(viewLifecycleOwner) { history ->
            autoCompleteAdapter.apply {
                clear()
                addAll(history.map { it.content })
            }
        }

        viewModel.searchResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                searchAdapter.submitList(it.results)
            }
        }
    }

    private fun setupAutoCompleteTextView() {
        binding.autoCompleteTextView.apply {
            setAdapter(autoCompleteAdapter)

            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val inputMethodManager =
                        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    inputMethodManager?.hideSoftInputFromWindow(applicationWindowToken, 0)
                    this.clearFocus()
                    viewModel.search(this.text.toString())
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun setupRecyclerViewSearchResult() {
        binding.recyclerView.apply {
            adapter = searchAdapter
            addItemDecoration(LinearVerticalItemDecoration(resources.getDimensionPixelOffset(R.dimen.dp_8)))
        }
    }

    private fun onSearchResultClick(item: SearchResponse.Result, position: Int) {

    }

    companion object {
        const val TAG = "SearchFragment"

        fun newInstance() = SearchFragment()
    }
}