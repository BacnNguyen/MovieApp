package gst.trainingcourse.movie.data.repo

import com.google.gson.Gson
import gst.trainingcourse.movie.helper.SharedPreferencesHelper
import javax.inject.Inject

class SharedPrefRepoImpl @Inject constructor(
    private val helper: SharedPreferencesHelper,
    private val gson: Gson
) : SharedPrefRepo {

}