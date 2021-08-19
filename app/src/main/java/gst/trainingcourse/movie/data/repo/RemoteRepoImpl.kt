package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.remote.ApiService
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(private val api: ApiService) : RemoteRepo
