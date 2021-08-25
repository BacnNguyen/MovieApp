package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.SearchResponse
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.data.model.YoutubeResponse

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

    suspend fun getMovieDetail(movieId:Int): MovieResponse.Movie

    suspend fun getRecommendMovie(movieId: Int):MovieResponse

    suspend fun getMovieTrailer(movieId: Int): YoutubeResponse

    suspend fun getTVShowDetail(showId:Int): TvShowResponse.TvShow

    suspend fun getRecommendTVShow(showId: Int):TvShowResponse

    suspend fun getTVShowTrailer(showId: Int): YoutubeResponse
}
