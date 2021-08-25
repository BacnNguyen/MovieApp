package gst.trainingcourse.movie.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import gst.trainingcourse.movie.R
import gst.trainingcourse.movie.utils.TransitionDirection

abstract class BaseFragment<FragmentViewBinding : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
    Fragment() {

    protected lateinit var binding: FragmentViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.root.apply {
            isClickable = true
            isFocusable = true
        }
        return binding.root
    }

    private fun FragmentTransaction.setAnimationDirection(direction: TransitionDirection) {
        when (direction) {
            TransitionDirection.RIGHT -> {
                setCustomAnimations(
                    R.anim.right_in,
                    R.anim.left_out,
                    R.anim.left_in,
                    R.anim.right_out
                )
            }
            TransitionDirection.BOTTOM -> {
                setCustomAnimations(
                    R.anim.bottom_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.bottom_out
                )
            }
            TransitionDirection.FADE -> {
                setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
            TransitionDirection.TOP -> {
                setCustomAnimations(
                    R.anim.top_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.top_out
                )
            }
            TransitionDirection.NONE -> {
            }
        }
    }
}
