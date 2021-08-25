package gst.trainingcourse.movie.data.remote

import gst.trainingcourse.movie.BuildConfig
import gst.trainingcourse.movie.data.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val LANGUAGE_EN = "en-US"
        const val LANGUAGE_VN = "vi"
        const val MEDIA_TYPE_MOVIE = "movie"
        const val MEDIA_TYPE_TV = "tv"
        const val MEDIA_TYPE_All = "all"
        const val MEDIA_TYPE_PERSON = "person"
        const val TIME_WINDOW_DAY = "day"
        const val TIME_WINDOW_WEEK = "week"
        const val REGION_VN = "vn"
        const val REGION_USA = "en-US"
        const val APPEND_TO_RESPONSE_IMAGES = "images"
    }

    //Begin movie

    @GET("trending/movie/{time_window}")
    fun getTrendingMovie(
        @Path("time_window") timeWindow: String = TIME_WINDOW_DAY,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1,
        @Query("region") region: String = REGION_VN
    ): Call<MovieResponse>

    @GET("movie/{movie_id}/videos")
    fun getYoutubeTrailerOfMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): Call<YoutubeResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): Call<MovieResponse.Movie>

    @GET("movie/{movie_id}/recommendations")
    fun getMovieRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("region") region: String = REGION_VN,
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getMovieTopRated(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("region") region: String = REGION_VN,
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getMovieUpcoming(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    //End movie

    //Begin TV show
    @GET("trending/tv/{time_window}")
    fun getTrendingTvShow(
        @Path("time_window") timeWindow: String = TIME_WINDOW_DAY,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowResponse>

    @GET("tv/popular")
    fun getPopularShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1
    ): Call<TvShowResponse>

    @GET("tv/{tv_id}/videos")
    fun getYoutubeTrailerOfShow(
        @Path("tv_id") showId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): Call<YoutubeResponse>

    @GET("tv/{tv_id}/recommendations")
    fun getShowRecommendations(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1
    ): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getShowDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): Call<TvShowResponse.TvShow>

    @GET("tv/top_rated")
    fun getTVShowTopRated(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1
    ): Call<TvShowResponse>

    @GET("tv/airing_today")
    fun getTVShowAiringToday(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1
    ): Call<TvShowResponse>

    @GET("tv/on_the_air")
    fun getTVShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1
    ): Call<TvShowResponse>

    //End TV show

    //Begin person

    @GET("trending/person/{time_window}")
    fun getTrendingPerson(
        @Path("time_window") timeWindow: String = TIME_WINDOW_DAY,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<PersonResponse>

    @GET("person/{person_id}")
    fun getPersonDetail(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("append_to_response") appendToResponse: String = APPEND_TO_RESPONSE_IMAGES
    ): Call<PersonDetail>

    //End person

    //Other

    @GET("search/multi")
    fun getSearchResult(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int = 1,
        @Query("query") query: String = ""
    ): Call<SearchResponse>


}

