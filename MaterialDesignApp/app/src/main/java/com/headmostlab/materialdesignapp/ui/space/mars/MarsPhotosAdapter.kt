package com.headmostlab.materialdesignapp.ui.space.mars

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MarsPhotosAdapter(
    private val listener: OnItemListener
) :
    ListAdapter<MarsPhotoUi, MarsPhotoViewHolder>(DIFF_CALLBACK), ItemTouchHelperAdapter {

    val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(this))

    private val dragListener: (RecyclerView.ViewHolder) -> Unit = {
        itemTouchHelper.startDrag(it)
    }

    private val handler = object : OnItemListener {
        override fun onClick(position: Int) {
            if (position != RecyclerView.NO_POSITION) {
                listener.onClick(position)
            }
        }

        override fun onDelete(position: Int) {
            if (position != RecyclerView.NO_POSITION) {
                listener.onDelete(position)
            }
        }

        override fun onMove(fromPosition: Int, toPosition: Int) {
            listener.onMove(fromPosition, toPosition)
        }

        override fun onShowDescription(position: Int) {
            if (position != RecyclerView.NO_POSITION) {
                listener.onShowDescription(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder =
        MarsPhotoViewHolder.create(parent, handler, dragListener)

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: MarsPhotoViewHolder) {
        super.onViewRecycled(holder)
        holder.recycled()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MarsPhotoUi>() {
            override fun areItemsTheSame(oldItem: MarsPhotoUi, newItem: MarsPhotoUi): Boolean =
                oldItem.photo.id == newItem.photo.id

            override fun areContentsTheSame(oldItem: MarsPhotoUi, newItem: MarsPhotoUi): Boolean =
                oldItem == newItem
        }
    }

    interface OnItemListener {
        fun onClick(position: Int)
        fun onDelete(position: Int)
        fun onMove(fromPosition: Int, toPosition: Int)
        fun onShowDescription(position: Int)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listener.onMove(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        listener.onDelete(position)
    }
}
