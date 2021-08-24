package gst.trainingcourse.movie.ui.fragment.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.repo.RemoteRepo
import gst.trainingcourse.movie.ui.fragment.detail.test_model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val remoteRepo: RemoteRepo): ViewModel() {
    var movie = MutableLiveData<MovieResponse.Movie>()
    var movies_recommend = MutableLiveData<List<MovieResponse.Movie>>()
    var comments = MutableLiveData<List<Comment>>()

    //data test comment
    val data_comment = Comment("user1","good movie")


    fun fetchData(movieId:Int){
        comments.value = listOf(data_comment,data_comment,data_comment,data_comment,data_comment)

        viewModelScope.launch(Dispatchers.IO){
            val result = remoteRepo.getMovieDetail(movieId)
            Log.i("data", "fetchData: ${result}")
            movie.postValue(result)
        }

        viewModelScope.launch(Dispatchers.IO){
            val result = remoteRepo.getRecommendMovie(movieId)
            val recommends = result.movies
            movies_recommend.postValue(recommends)
        }
    }
}