package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.model.SearchHistory
import gst.trainingcourse.movie.data.room.AppDao
import javax.inject.Inject

class LocalRepoImpl @Inject constructor(private val appDao: AppDao) :
    LocalRepo {

    override fun getAllHistory() = appDao.getAllHistory()

    override fun saveHistory(history: SearchHistory) {
        appDao.insert(history)
    }
}
