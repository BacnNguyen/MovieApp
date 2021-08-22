package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.model.SearchResponse
import gst.trainingcourse.movie.data.remote.ApiService
import gst.trainingcourse.movie.utils.extract
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(private val api: ApiService) : RemoteRepo {

    override suspend fun getMovieTrending() = api.getTrendingMovie().extract()

    override suspend fun getTVShowTrending() = api.getTrendingTvShow().extract()

    override suspend fun getPopularMovie() = api.getPopularMovie().extract()

    override suspend fun getMovieNowPlaying() = api.getMovieNowPlaying().extract()

    override suspend fun getMovieTopRated() = api.getMovieTopRated().extract()

    override suspend fun getMovieUpcoming() = api.getMovieUpcoming().extract()

    override suspend fun getPopularShow() = api.getPopularShow().extract()

    override suspend fun getTVShowTopRated() = api.getTVShowTopRated().extract()

    override suspend fun getTVShowAiringToday() = api.getTVShowAiringToday().extract()

    override suspend fun getTVShowOnTheAir() = api.getTVShowOnTheAir().extract()

    override suspend fun search(query: String) = api.getSearchResult(query = query).extract()
}
