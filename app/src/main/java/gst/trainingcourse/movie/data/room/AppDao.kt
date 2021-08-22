package gst.trainingcourse.movie.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gst.trainingcourse.movie.data.model.SearchHistory

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchHistory: SearchHistory)

    @Query("SELECT * FROM search_history_table ORDER BY time DESC")
    fun getAllHistory(): LiveData<List<SearchHistory>>

}