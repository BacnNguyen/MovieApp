package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.remote.ApiService
import gst.trainingcourse.movie.utils.extract
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(private val api: ApiService) : RemoteRepo {

    override suspend fun getMovieTrending() = api.getTrendingMovie().extract()

    override suspend fun getTVShowTrending() = api.getTrendingTvShow().extract()
}
