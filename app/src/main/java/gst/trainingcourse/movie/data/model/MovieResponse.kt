package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val movies: List<Movie> = listOf(),

    @SerializedName("page")
    val page: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0
) : Parcelable {

    @Parcelize
    data class Movie(
        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("vote_average")
        val voteAverage: Double = 0.0,

        @SerializedName("title")
        val title: String = "",

        @SerializedName("release_date")
        val releaseDate: String = "",

        @SerializedName("genres")
        val genres: List<Genre> = listOf(),

        @SerializedName("overview")
        val overview: String = "",

        @SerializedName("poster_path")
        val posterPath: String = "",

        @SerializedName("homepage")
        val homepage: String = "",

        @SerializedName("budget")
        val budget: Int = 0
    ) : Parcelable

}