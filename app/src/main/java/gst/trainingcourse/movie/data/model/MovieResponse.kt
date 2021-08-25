package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
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
    @Entity(tableName = "movie_favorite")
    data class Movie(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        var id: Int = 0,

        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,

        @SerializedName("title")
        var title: String = "",

        @SerializedName("release_date")
        var releaseDate: String = "",

        @Ignore
        @SerializedName("genres")
        val genres: List<Genre> = listOf(),

        @SerializedName("overview")
        var overview: String = "",

        @SerializedName("poster_path")
        var posterPath: String = "",

        @SerializedName("homepage")
        var homepage: String = "",

        @SerializedName("budget")
        var budget: Int = 0
    ) : Parcelable

}