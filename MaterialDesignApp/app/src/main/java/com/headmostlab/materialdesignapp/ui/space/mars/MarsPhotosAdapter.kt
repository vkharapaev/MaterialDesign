package com.headmostlab.materialdesignapp.ui.space.mars

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto

class MarsPhotosAdapter : ListAdapter<MarsPhoto, MarsPhotoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder =
        MarsPhotoViewHolder.create(parent)

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: MarsPhotoViewHolder) {
        super.onViewRecycled(holder)
        holder.recycled()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MarsPhoto>() {
            override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
                oldItem == newItem

        }
    }
}
