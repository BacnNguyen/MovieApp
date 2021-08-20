package gst.trainingcourse.movie.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.data.repo.LocalRepo
import gst.trainingcourse.movie.data.repo.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val remoteRepo: RemoteRepo) : ViewModel() {

    val movies = MutableLiveData<List<MovieResponse.Movie>>(emptyList())

    val tvShow = MutableLiveData<List<TvShowResponse.TvShow>>(emptyList())

    init {
        fetchMovieTrending()
        fetchTVShowTrending()
    }

    private fun fetchMovieTrending() {
        viewModelScope.launch(Dispatchers.IO) {
            movies.postValue(remoteRepo.getMovieTrending().movies)
        }
    }

    private fun fetchTVShowTrending() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShow.postValue(remoteRepo.getTVShowTrending().results)
        }
    }
}