package com.headmostlab.materialdesignapp.ui.space.mars

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.headmostlab.materialdesignapp.App
import com.headmostlab.materialdesignapp.DI
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.MarsFragmentBinding
import com.headmostlab.materialdesignapp.ui.utils.viewBinding

class MarsFragment : Fragment(R.layout.mars_fragment) {

    private val binding by viewBinding(MarsFragmentBinding::bind)

    private val component by lazy { DI.appComponent.getMarsScreenComponentFactory().build() }

    private val viewModel by viewModels<MarsViewModel> { component.viewModelFactory() }

    private var adapter: MarsPhotosAdapter? = null

    override fun onDestroyView() {
        super.onDestroyView()

        adapter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = MarsPhotosAdapter { viewModel.selectPhoto(it) }

        binding.photosRecyclerView.adapter = adapter

        viewModel.photos.observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.onShowPicture.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                Glide.with(App.instance)
                    .load(it.imgSrc)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(GlideListener())
                    .into(binding.largeImage)
            }
        }

        binding.largeImage.setOnClickListener {
            if (it.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(binding.transitionContainer)
                binding.largeImage.visibility = View.INVISIBLE
            }
        }

    }

    private fun render(state: MarsPhotosState) {
        when (state) {

            MarsPhotosState.Loading -> {
            }

            is MarsPhotosState.Success -> adapter?.submitList(state.data)

            is MarsPhotosState.Error ->
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()

        }
    }

    private inner class GlideListener : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            TransitionManager.beginDelayedTransition(binding.transitionContainer)
            binding.largeImage.visibility = View.VISIBLE
            return false
        }
    }

}
