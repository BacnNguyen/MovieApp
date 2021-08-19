package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class YoutubeResponse(
    @SerializedName("results")
    val results: List<Youtube> = listOf()
) : Parcelable {

    @Parcelize
    data class Youtube(
        @SerializedName("id")
        val id: String = "",

        @SerializedName("key")
        val key: String = "",

        @SerializedName("type")
        val type: String = "",

        @SerializedName("site")
        val site: String = ""
    ) : Parcelable

}