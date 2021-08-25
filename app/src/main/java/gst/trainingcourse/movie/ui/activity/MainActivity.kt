package gst.trainingcourse.movie.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.ui.fragment.movie_detail.MovieDetailFragment
import gst.trainingcourse.movie.ui.fragment.tv_show_detail.TVShowDetailFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, TVShowDetailFragment.newInstance(1402), TVShowDetailFragment.TAG)
            .commit()
    }
}