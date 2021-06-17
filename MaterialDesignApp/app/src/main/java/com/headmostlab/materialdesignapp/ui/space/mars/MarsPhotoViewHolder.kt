package com.headmostlab.materialdesignapp.ui.space.mars

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.Disposable
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.MarsPhotoItemBinding
import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto

class MarsPhotoViewHolder(val binding: MarsPhotoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var disposable: Disposable? = null

    fun bind(marsPhoto: MarsPhoto) {
        disposable = binding.image.load(marsPhoto.imgSrc) {
        }
    }

    fun recycled() {
        disposable?.dispose()
    }

    companion object {
        fun create(parent: ViewGroup): MarsPhotoViewHolder =
            MarsPhotoViewHolder(MarsPhotoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
}