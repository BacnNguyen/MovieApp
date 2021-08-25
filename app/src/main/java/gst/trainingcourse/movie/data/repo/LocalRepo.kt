package gst.trainingcourse.movie.data.repo

import androidx.lifecycle.LiveData
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.SearchHistory
import gst.trainingcourse.movie.data.model.TvShowResponse

interface LocalRepo {

    fun getAllHistory(): LiveData<List<SearchHistory>>

    fun saveHistory(history: SearchHistory)

    fun saveMovie(movie: MovieResponse.Movie)

    fun getMovie(movieId: Int): MovieResponse.Movie

    fun deleteMovie(movieId: Int)

    fun saveShow(show: TvShowResponse.TvShow)

    fun getShow(showId: Int): TvShowResponse.TvShow

    fun deleteShow(showId: Int)
}
