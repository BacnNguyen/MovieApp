package gst.trainingcourse.movie.ui.fragment.category.movie.movieTopRated

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.repo.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieTopRatedVIewModel @Inject constructor(private val remoteRepo: RemoteRepo) :ViewModel() {
    val movies = MutableLiveData<List<MovieResponse.Movie>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            movies.postValue(remoteRepo.getMovieTopRated().movies)
        }
    }


}