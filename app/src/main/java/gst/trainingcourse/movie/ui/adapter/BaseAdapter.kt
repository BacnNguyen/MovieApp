package gst.trainingcourse.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, ItemBinding : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
    ListAdapter<T, BaseAdapter<T, ItemBinding>.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }

        override fun getChangePayload(oldItem: T, newItem: T): Any {
            return 0
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemBinding>(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        notNeedRebind(holder, position)
        needRebind(holder, position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.firstOrNull() is Int) {
            needRebind(holder, position)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    abstract fun needRebind(holder: ViewHolder, position: Int)
    abstract fun notNeedRebind(holder: ViewHolder, position: Int)

    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

}