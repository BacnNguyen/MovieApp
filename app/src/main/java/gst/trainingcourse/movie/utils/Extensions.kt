package gst.trainingcourse.movie.utils

import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

const val API_TAG = "API_TAG"
suspend fun <T> Call<T>.extract(): T {
    return suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.e(API_TAG, "Error Data! ${t.message}")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful && response.body() != null) {
                    continuation.resume(response.body()!!)
                } else {
                    Log.e(API_TAG, "Error Data! ${response.message()}")
                }
            }
        })
    }
}

fun View.setSingleClick(timeout: Long = 1000L, callback: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(view: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < timeout) {
                return
            }
            lastClickTime = SystemClock.elapsedRealtime()
            callback()
        }
    })
}

fun View.setOnSingleTouch(timeout: Long = 1000L, callback: (event : MotionEvent) -> Unit) {
    setOnTouchListener(object : View.OnTouchListener {
        var lastClickTime: Long = 0
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            if (SystemClock.elapsedRealtime() - lastClickTime < timeout) {
                return false
            }
            lastClickTime = SystemClock.elapsedRealtime()
            callback(event!!)
            return true
        }
    })
}