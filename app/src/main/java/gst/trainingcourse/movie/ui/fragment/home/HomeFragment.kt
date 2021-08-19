package gst.trainingcourse.movie.ui.fragment.home

import dagger.hilt.android.AndroidEntryPoint
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.databinding.FragmentHomeBinding
import gst.trainingcourse.movie.ui.fragment.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }
}