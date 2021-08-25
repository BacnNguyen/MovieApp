package gst.trainingcourse.movie.ui.fragment.movie_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.YoutubeResponse
import gst.trainingcourse.movie.data.repo.LocalRepo
import gst.trainingcourse.movie.data.repo.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val remoteRepo: RemoteRepo,
    private val localRepo: LocalRepo
) : ViewModel() {
    val movieDetail = MutableLiveData<MovieResponse.Movie>()
    val movieRecommend = MutableLiveData<List<MovieResponse.Movie>>()
    val trailer = MutableLiveData<YoutubeResponse>()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetail.postValue(remoteRepo.getMovieDetail(movieId))
        }
    }

    fun getMovieRecommend(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRecommend.postValue(remoteRepo.getRecommendMovie(movieId).movies)
        }
    }

    fun getTrailer(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            trailer.postValue(remoteRepo.getMovieTrailer(movieId))
        }
    }

    fun saveMovie(movie: MovieResponse.Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepo.saveMovie(movie)
        }
    }

    fun deleteMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepo.deleteMovie(movieId)
        }
    }

    fun getMovie(movieId: Int) = localRepo.getMovie(movieId)
}