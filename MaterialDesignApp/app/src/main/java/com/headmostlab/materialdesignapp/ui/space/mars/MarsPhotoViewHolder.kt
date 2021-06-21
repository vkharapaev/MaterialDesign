package com.headmostlab.materialdesignapp.ui.space.mars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.headmostlab.materialdesignapp.App
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.MarsPhotoItemBinding
import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto

class MarsPhotoViewHolder(
    private val binding: MarsPhotoItemBinding,
    private val clickListener: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(marsPhoto: MarsPhoto) {

        Glide.with(App.instance)
            .load(marsPhoto.imgSrc)
            .placeholder(R.drawable.ic_no_photo_vector)
            .into(binding.image)

        binding.image.setOnClickListener {
            clickListener(adapterPosition)
        }
    }

    fun recycled() {
        Glide.with(App.instance).clear(binding.image)
    }

    companion object {
        fun create(parent: ViewGroup, clickListener: (Int) -> Unit): MarsPhotoViewHolder =
            MarsPhotoViewHolder(
                MarsPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                clickListener
            )
    }
}