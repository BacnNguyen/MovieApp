package gst.trainingcourse.movie.data.repo

import androidx.lifecycle.LiveData
import gst.trainingcourse.movie.data.model.*
import gst.trainingcourse.movie.data.room.AppDao
import javax.inject.Inject

class LocalRepoImpl @Inject constructor(private val appDao: AppDao) :
    LocalRepo {

    override fun getAllHistory() = appDao.getAllHistory()

    override fun saveHistory(history: SearchHistory) {
        appDao.insert(history)
    }

    override fun saveMovie(movie: MovieResponse.Movie) {
        appDao.insert(movie)
    }

    override fun getMovie(movieId: Int) = appDao.getMovie(movieId)

    override fun deleteMovie(movieId: Int) {
        appDao.deleteMovie(movieId)
    }

    override fun saveShow(show: TvShowResponse.TvShow) {
        appDao.insert(show)
    }

    override fun getShow(showId: Int) = appDao.getTVShow(showId)

    override fun deleteShow(showId: Int) {
        appDao.deleteTVShow(showId)
    }

    override fun getAllMovie() = appDao.getAllMovie()

    override fun getAllTVShow() = appDao.getAllTVShow()
    override fun saveRecommend(list: List<Recommend>) {
        appDao.insertRecommend(list)
    }

    override fun getAllRecommend(): LiveData<List<Recommend>> = appDao.getAllRecommend()
}
