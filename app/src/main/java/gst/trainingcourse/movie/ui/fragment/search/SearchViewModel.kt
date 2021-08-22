package gst.trainingcourse.movie.ui.fragment.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.SearchHistory
import gst.trainingcourse.movie.data.model.SearchResponse
import gst.trainingcourse.movie.data.repo.LocalRepo
import gst.trainingcourse.movie.data.repo.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val localRepo: LocalRepo,
    private val remoteRepo: RemoteRepo
) : ViewModel() {

    val history by lazy { localRepo.getAllHistory() }

    val searchResponse = MutableLiveData<SearchResponse>()

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchResponse.postValue(remoteRepo.search(query))
            saveHistory(query)
        }
    }

    private fun saveHistory(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepo.saveHistory(SearchHistory(query, System.currentTimeMillis()))
        }
    }
}