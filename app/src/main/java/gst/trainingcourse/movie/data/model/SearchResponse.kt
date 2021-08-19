package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("page")
    val page: Int = 0,

    @SerializedName("total_results")
    val totalResults: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0,

    @SerializedName("results")
    val results: List<Result> = listOf()
) : Parcelable {

    @Parcelize
    data class Result(
        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("name")
        val name: String = "",

        @SerializedName("title")
        val title: String = "",

        @SerializedName("profile_path")
        val profilePath: String = "",

        @SerializedName("poster_path")
        val posterPath: String = "",

        @SerializedName("media_type")
        val mediaType: String = ""
    ) : Parcelable

}