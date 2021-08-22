package gst.trainingcourse.movie.data.repo

import androidx.lifecycle.LiveData
import gst.trainingcourse.movie.data.model.SearchHistory

interface LocalRepo {

    fun getAllHistory(): LiveData<List<SearchHistory>>

    fun saveHistory(history: SearchHistory)
}
