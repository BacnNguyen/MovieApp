package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonDetail(
    @SerializedName("birthday")
    val birthDay: String = "",

    @SerializedName("known_for_department")
    val knownForDepartment: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("images")
    val images: Image = Image(),

    @SerializedName("biography")
    val biography: String = ""
) : Parcelable {

    @Parcelize
    data class Image(
        @SerializedName("profiles")
        val images: List<Profile> = listOf()
    ) : Parcelable {

        @Parcelize
        data class Profile(
            @SerializedName("file_path")
            val filePath: String = "",

            @SerializedName("height")
            val height: Int = 0,

            @SerializedName("width")
            val width: Int = 0
        ) : Parcelable

    }

}