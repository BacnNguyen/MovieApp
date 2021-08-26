package gst.trainingcourse.movie.ui.fragment.tv_show_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.Recommend
import gst.trainingcourse.movie.data.model.TvShowResponse
import gst.trainingcourse.movie.data.model.YoutubeResponse
import gst.trainingcourse.movie.data.repo.LocalRepo
import gst.trainingcourse.movie.data.repo.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel @Inject constructor(
    private val remoteRepo: RemoteRepo,
    private val localRepo: LocalRepo
) : ViewModel() {
    val tvShowDetail = MutableLiveData<TvShowResponse.TvShow>()
    val tvShowRecommend = MutableLiveData<List<TvShowResponse.TvShow>>()
    val trailer = MutableLiveData<YoutubeResponse>()

    fun getTVShowDetail(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowDetail.postValue(remoteRepo.getTVShowDetail(showId))
        }
    }

    fun getTVShowRecommend(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowRecommend.postValue(remoteRepo.getRecommendTVShow(showId).results)
        }
    }

    fun getTrailer(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            trailer.postValue(remoteRepo.getTVShowTrailer(showId))
        }
    }

    fun saveShow(show: TvShowResponse.TvShow) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepo.saveShow(show)
        }
    }

    fun saveRecommendShow() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowRecommend.value?.let { list ->
                localRepo.saveRecommend(
                    list.map {
                        Recommend(
                            id = it.id,
                            isMovie = false,
                            thumbNail = it.posterPath
                        )
                    }
                )
            }
        }
    }

    fun deleteTVShow(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepo.deleteShow(showId)
        }
    }

    fun getTVShow(showId: Int) = localRepo.getShow(showId)
}