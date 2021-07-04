package com.headmostlab.materialdesignapp.ui.space.mars

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.headmostlab.materialdesignapp.App
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.MarsListItemBinding

class MarsPhotoViewHolder(
    private val binding: MarsListItemBinding,
    private val listener: MarsPhotosAdapter.OnItemListener,
    private val dragListener: (RecyclerView.ViewHolder) -> Unit
) :
    RecyclerView.ViewHolder(binding.root), ItemTouchHelperViewHolder {

    private val defaultColor by lazy {
        val typedValue = TypedValue()
        val theme = binding.root.context.theme
        theme.resolveAttribute(R.attr.colorOnPrimary, typedValue, true)
        typedValue.data
    }

    fun bind(marsPhoto: MarsPhotoUi) {
        binding.descriptionTextView.visibility =
            if (marsPhoto.isDescriptionVisible) View.VISIBLE else View.GONE

        Glide.with(App.instance)
            .load(marsPhoto.photo.imgSrc)
            .placeholder(R.drawable.ic_no_photo_vector)
            .into(binding.image)

        binding.image.setOnClickListener {
            listener.onClick(adapterPosition)
        }

        binding.deleteButton.setOnClickListener {
            listener.onDelete(adapterPosition)
        }

        binding.moveDownButton.setOnClickListener {
            listener.onMove(adapterPosition, adapterPosition + 1)
        }

        binding.infoButton.setOnClickListener {
            listener.onShowDescription(adapterPosition)
        }

        binding.moveButton.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                dragListener(this)
            }
            true
        }
    }

    fun recycled() {
        Glide.with(App.instance).clear(binding.image)
    }

    companion object {
        fun create(
            parent: ViewGroup,
            clickListener: MarsPhotosAdapter.OnItemListener,
            dragListener: (RecyclerView.ViewHolder) -> Unit
        ): MarsPhotoViewHolder =
            MarsPhotoViewHolder(
                MarsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                clickListener, dragListener
            )
    }

    override fun onItemSelected() {
        binding.item.setBackgroundColor(Color.LTGRAY)
    }

    override fun onItemClear() {
        binding.item.setBackgroundColor(defaultColor)
    }
}