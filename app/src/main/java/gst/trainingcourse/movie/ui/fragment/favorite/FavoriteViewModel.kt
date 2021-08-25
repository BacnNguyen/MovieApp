package gst.trainingcourse.movie.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.repo.LocalRepo
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val localRepo: LocalRepo) : ViewModel() {

    fun getAllMovie() = localRepo.getAllMovie()

    fun getAllTVShow() = localRepo.getAllTVShow()

}