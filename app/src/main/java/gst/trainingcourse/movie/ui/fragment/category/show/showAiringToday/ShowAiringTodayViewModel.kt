package gst.trainingcourse.movie.ui.fragment.category.show.showAiringToday

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.data.repo.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowAiringTodayViewModel @Inject constructor(private val remoteRepo: RemoteRepo) :ViewModel() {
    val movies = MutableLiveData<List<TvShowResponse.TvShow>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            movies.postValue(remoteRepo.getTVShowAiringToday().results)
        }
    }


}