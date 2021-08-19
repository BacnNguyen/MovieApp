package gst.trainingcourse.movie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "search_history_table")
class SearchHistory(
    @PrimaryKey(autoGenerate = false)
    val content: String = "",

    val time: Long = 0
)