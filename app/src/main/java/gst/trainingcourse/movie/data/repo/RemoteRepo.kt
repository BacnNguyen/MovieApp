package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.SearchResponse
import gst.trainingcourse.movie.data.model.TvShowResponse

interface RemoteRepo {
    suspend fun getMovieTrending(): MovieResponse

    suspend fun getTVShowTrending(): TvShowResponse

    suspend fun getPopularMovie(): MovieResponse

    suspend fun getMovieNowPlaying(): MovieResponse

    suspend fun getMovieTopRated(): MovieResponse

    suspend fun getMovieUpcoming(): MovieResponse

    suspend fun getPopularShow(): TvShowResponse

    suspend fun getTVShowTopRated(): TvShowResponse

    suspend fun getTVShowAiringToday(): TvShowResponse

    suspend fun getTVShowOnTheAir(): TvShowResponse

    suspend fun search(query: String): SearchResponse
}
