package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.room.AppDao
import javax.inject.Inject

class LocalRepoImpl @Inject constructor(private val appDao: AppDao) :
    LocalRepo {
}
