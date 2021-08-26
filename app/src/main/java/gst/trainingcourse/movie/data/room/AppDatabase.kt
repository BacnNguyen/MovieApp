package gst.trainingcourse.movie.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import gst.trainingcourse.movie.data.model.*


@Database(
    entities = [SearchHistory::class, MovieResponse.Movie::class, TvShowResponse.TvShow::class, Recommend::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}