package gst.trainingcourse.movie.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gst.trainingcourse.movie.data.model.MovieResponse
import gst.trainingcourse.movie.data.model.SearchHistory
import gst.trainingcourse.movie.data.model.TvShowResponse

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchHistory: SearchHistory)

    @Query("SELECT * FROM search_history_table ORDER BY time DESC")
    fun getAllHistory(): LiveData<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieResponse.Movie)

    @Query("SELECT * FROM movie_favorite WHERE id = :movieId")
    fun getMovie(movieId: Int): MovieResponse.Movie

    @Query("DELETE FROM movie_favorite WHERE id = :movieId")
    fun deleteMovie(movieId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(show: TvShowResponse.TvShow)

    @Query("SELECT * FROM show_favorite WHERE id = :showId")
    fun getTVShow(showId: Int): TvShowResponse.TvShow

    @Query("DELETE FROM show_favorite WHERE id = :showId")
    fun deleteTVShow(showId: Int)

    @Query("SELECT * FROM movie_favorite")
    fun getAllMovie(): LiveData<List<MovieResponse.Movie>>

    @Query("SELECT * FROM show_favorite")
    fun getAllTVShow(): LiveData<List<TvShowResponse.TvShow>>
}