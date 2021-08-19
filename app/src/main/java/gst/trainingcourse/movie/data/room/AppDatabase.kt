package gst.trainingcourse.movie.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import gst.trainingcourse.movie.data.model.SearchHistory


@Database(entities = [SearchHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}