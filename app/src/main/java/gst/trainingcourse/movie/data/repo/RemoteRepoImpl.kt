package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.data.model.YoutubeResponse
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

    override suspend fun getMovieDetail(movieId: Int) = api.getMovieDetail(movieId).extract()

    override suspend fun getRecommendMovie(movieId: Int) =
        api.getMovieRecommendations(movieId).extract()

    override suspend fun getMovieTrailer(movieId: Int) =
        api.getYoutubeTrailerOfMovie(movieId).extract()

    override suspend fun getTVShowDetail(showId: Int) = api.getShowDetail(showId).extract()

    override suspend fun getRecommendTVShow(showId: Int) =
        api.getShowRecommendations(showId).extract()

    override suspend fun getTVShowTrailer(showId: Int) =
        api.getYoutubeTrailerOfShow(showId).extract()
}
