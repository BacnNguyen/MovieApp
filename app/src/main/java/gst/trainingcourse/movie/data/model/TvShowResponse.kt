package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
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

    @Entity(tableName = "show_favorite")
    @Parcelize
    data class TvShow(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        var id: Int = 0,

        @SerializedName("name")
        var title: String = "",

        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,

        @SerializedName("first_air_date")
        var firstAirDate: String = "",

        @SerializedName("poster_path")
        var posterPath: String = "",

        @Ignore
        @SerializedName("genres")
        val genres: List<Genre> = listOf(),

        @SerializedName("overview")
        var overview: String = "",

        @SerializedName("number_of_seasons")
        var numberOfSeasons: Int = 0,

        @SerializedName("number_of_episodes")
        var numberOfEpisodes: Int = 0,

        @SerializedName("homepage")
        var homepage: String = ""
    ) : Parcelable

}