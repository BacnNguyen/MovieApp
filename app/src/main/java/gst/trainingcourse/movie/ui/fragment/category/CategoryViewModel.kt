package gst.trainingcourse.movie.ui.fragment.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gst.trainingcourse.movie.data.model.Category
import gst.trainingcourse.movie.data.repo.RemoteRepo
import gst.trainingcourse.movie.utils.extract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val remoteRepo: RemoteRepo) : ViewModel() {

    private val _categories = mutableListOf<Category>()

    val categories = MutableLiveData<List<Category>>(emptyList())

    init {
        fetchCategory()
    }

    private fun fetchCategory() {
        val functions = listOf(
            this::getPopularMovie,
            this::getMovieNowPlaying,
            this::getMovieTopRated,
            this::getMovieUpcoming,
            this::getPopularShow,
            this::getTVShowTopRated,
            this::getTVShowAiringToday,
            this::getTVShowOnTheAir
        )

        viewModelScope.launch(Dispatchers.IO) {
            functions.map {
                async {
                    it.invoke()?.let { _categories.add(it) }
                }
            }.awaitAll()
            categories.postValue(_categories)
        }
    }

    private suspend fun getPopularMovie(): Category? {
        return remoteRepo.getPopularMovie().movies.firstOrNull()?.let {
            Category("Movie Popular", it.posterPath)
        }
    }

    private suspend fun getMovieNowPlaying(): Category? {
        return remoteRepo.getMovieNowPlaying().movies.firstOrNull()?.let {
            Category("Movie Now Playing", it.posterPath)
        }
    }

    private suspend fun getMovieTopRated(): Category? {
        return remoteRepo.getMovieTopRated().movies.firstOrNull()?.let {
            Category("Movie Top Rated", it.posterPath)
        }
    }

    private suspend fun getMovieUpcoming(): Category? {
        return remoteRepo.getMovieUpcoming().movies.firstOrNull()?.let {
            Category("Movie Upcoming", it.posterPath)
        }
    }

    private suspend fun getPopularShow(): Category? {
        return remoteRepo.getPopularShow().results.firstOrNull()?.let {
            Category("TV Show Popular", it.posterPath)
        }
    }

    private suspend fun getTVShowTopRated(): Category? {
        return remoteRepo.getTVShowTopRated().results.firstOrNull()?.let {
            Category("TV Show Top Rated", it.posterPath)
        }
    }

    private suspend fun getTVShowAiringToday(): Category? {
        return remoteRepo.getTVShowAiringToday().results.firstOrNull()?.let {
            Category("TV Show Airing Today", it.posterPath)
        }
    }

    private suspend fun getTVShowOnTheAir(): Category? {
        return remoteRepo.getTVShowOnTheAir().results.firstOrNull()?.let {
            Category("TV Show On The Air", it.posterPath)
        }
    }

}