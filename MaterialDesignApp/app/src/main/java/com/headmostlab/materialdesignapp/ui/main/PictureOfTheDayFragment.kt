package com.headmostlab.materialdesignapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.headmostlab.materialdesignapp.DI
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.PictureOfTheDayFragmentBinding
import com.headmostlab.materialdesignapp.ui.utils.showSnackbar
import com.headmostlab.materialdesignapp.ui.utils.viewBinding
import com.headmostlab.materialdesignapp.utils.DateTimeUtils
import java.util.*

class PictureOfTheDayFragment : Fragment(R.layout.picture_of_the_day_fragment) {

    companion object {
        private var isMain = true
    }

    private val component by lazy {
        DaggerPictureOfTheDayScreenComponent.builder()
            .setPictureOfTheDayRepository(DI.appComponent.getPictureOfTheDayRepository())
            .build()
    }

    private val binding by viewBinding(PictureOfTheDayFragmentBinding::bind)

    private val viewModel by viewModels<PictureOfTheDayViewModel> { component.viewModelFactory() }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        setUpEditText()
        setBottomSheetBehavior(binding.bottomSheet.bottomSheet)
        setUpBottomAppBar()
        setUpFab()
        setUpChipGroup()
        setUpWebView()
    }

    private fun setUpWebView() {
        val webViewSettings = binding.webView.settings
        webViewSettings.javaScriptEnabled = true
        webViewSettings.javaScriptCanOpenWindowsAutomatically = true
    }

    private fun setUpEditText() {
        binding.inputLayout.setEndIconOnClickListener {
            searchInWiki()
        }
        binding.inputEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchInWiki()
                    true
                }
                else -> false
            }
        }
    }

    private fun searchInWiki() {
        val searchText = binding.inputEditText.text.toString()
        if (searchText.isBlank()) return
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data =
                Uri.parse("https://en.wikipedia.org/wiki/${searchText.toString()}")
        })
    }

    private fun setUpChipGroup() {
        binding.chipGroup.setOnCheckedChangeListener { chipGroup, chipId ->
            val calendar = Calendar.getInstance()
            val daysBefore = when (chipId) {
                R.id.yesterdayChip -> -1
                R.id.twoDaysAgoChip -> -2
                else -> 0
            }
            calendar.add(Calendar.DAY_OF_MONTH, daysBefore)
            viewModel.getData(DateTimeUtils.format(calendar.time))
        }
    }

    private fun setUpFab() {
        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.bottom_appbar_menu_2)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hamburger_menu_bottom_bar
                    )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.bottom_appbar_menu)
            }
        }
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    showError(Throwable("Url is null"))
                } else {
                    binding.imageView.visibility = View.INVISIBLE
                    binding.webView.visibility = View.INVISIBLE
                    binding.webView.loadUrl("about:blank")
                    if (data.serverResponseData.media_type == "video") {
                        binding.webView.visibility = View.VISIBLE
                        binding.webView.loadUrl(url)
                    } else {
                        binding.imageView.visibility = View.VISIBLE
                        binding.imageView.load(url) {
                            lifecycle(this@PictureOfTheDayFragment)
                            error(R.drawable.ic_load_error_vector)
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
                    }
                }

                with(binding.bottomSheet) {
                    bottomSheetDescriptionHeader.text = serverResponseData.title
                    bottomSheetDescription.text = serverResponseData.explanation
                }
            }
            is PictureOfTheDayData.Loading -> {
                showLoading(data.progress)
            }
            is PictureOfTheDayData.Error -> {
                showError(data.error)
            }
        }
    }

    private fun showLoading(progress: Int?) {
//        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
    }

    private fun showError(error: Throwable) {
        error.message?.let { binding.main.showSnackbar(it) }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> Log.e(
                        "TAG",
                        "onStateChanged: STATE_DRAGGING"
                    )
                    BottomSheetBehavior.STATE_SETTLING -> Log.e(
                        "TAG",
                        "onStateChanged: STATE_SETTLING"
                    )
                    BottomSheetBehavior.STATE_EXPANDED -> Log.e(
                        "TAG",
                        "onStateChanged: STATE_EXPANDED"
                    )
                    BottomSheetBehavior.STATE_COLLAPSED -> Log.e(
                        "TAG",
                        "onStateChanged: STATE_COLLAPSED"
                    )

                    BottomSheetBehavior.STATE_HIDDEN -> Log.e("TAG", "onStateChanged: STATE_HIDDEN")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> Log.e(
                        "TAG",
                        "onStateChanged: STATE_HALF_EXPANDED"
                    )
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.e("TAG", "onSlide: slideOffset $slideOffset")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_appbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        when (item.itemId) {
            R.id.app_bar_plantes ->
                navController.navigate(R.id.action_pictureOfTheDayFragment_to_planetsFragment)
            R.id.app_bar_settings ->
                navController.navigate(R.id.action_pictureOfTheDayFragment_to_settingsFragment)
            android.R.id.home ->
                navController.navigate(R.id.action_pictureOfTheDayFragment_to_bottomNavigationDrawerFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpBottomAppBar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }
}