package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonResponse(
    @SerializedName("results")
    val results: List<Person> = listOf(),

    @SerializedName("page")
    val page: Int = 0,

    @SerializedName("total_pages")
    val totalPages: Int = 0
) : Parcelable {
    @Parcelize
    data class Person(
        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("name")
        val name: String = "",

        @SerializedName("gender")
        val gender: Int = 0,

        @SerializedName("known_for_department")
        val knownForDepartment: String = "",

        @SerializedName("profile_path")
        val profilePath: String = "",

        @SerializedName("known_for")
        val knownFor: List<Film> = listOf()
    ) : Parcelable {
        @Parcelize
        class Film(
            @SerializedName("id")
            val id: Int = 0,

            @SerializedName("poster_path")
            val posterPath: String = "",

            @SerializedName("title")
            val title: String = "",

            @SerializedName("name")
            val name: String = "",

            @SerializedName("media_type")
            val mediaType: String = ""
        ) : Parcelable
    }
}
