package gst.trainingcourse.movie.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recommend")
data class Recommend(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("is_movie")
    var isMovie: Boolean = true,

    @SerializedName("thumbnail")
    var thumbNail: String = "",

    @SerializedName("timestamp")
    var timeStamp: Long = System.currentTimeMillis()
) : Parcelable