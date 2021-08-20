package gst.trainingcourse.movie.data.repo

import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.TvShowResponse

interface RemoteRepo {
    suspend fun getMovieTrending(): MovieResponse

    suspend fun getTVShowTrending(): TvShowResponse
}
