package gst.trainingcourse.movie.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.SearchHistory
import gst.trainingcourse.movie.data.model.TvShowResponse


@Database(
    entities = [SearchHistory::class, MovieResponse.Movie::class, TvShowResponse.TvShow::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}