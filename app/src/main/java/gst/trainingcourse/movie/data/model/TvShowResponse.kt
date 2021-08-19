package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    @SerializedName("results")
    val results: List<TvShow> = listOf(),

    @SerializedName("page")
    val page: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0
) : Parcelable {

    @Parcelize
    data class TvShow(
        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("name")
        val title: String = "",

        @SerializedName("vote_average")
        val voteAverage: Double = 0.0,

        @SerializedName("first_air_date")
        val firstAirDate: String = "",

        @SerializedName("poster_path")
        val posterPath: String = "",

        @SerializedName("genres")
        val genres: List<Genre> = listOf(),

        @SerializedName("overview")
        val overview: String = "",

        @SerializedName("number_of_seasons")
        val numberOfSeasons: Int = 0,

        @SerializedName("number_of_episodes")
        val numberOfEpisodes: Int = 0,

        @SerializedName("homepage")
        val homepage: String = ""
    ) : Parcelable

}