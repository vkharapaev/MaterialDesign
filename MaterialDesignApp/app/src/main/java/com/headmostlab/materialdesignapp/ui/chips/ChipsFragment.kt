package com.headmostlab.materialdesignapp.ui.chips

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.ChipsFragmentBinding
import com.headmostlab.materialdesignapp.ui.utils.viewBinding

class ChipsFragment : Fragment(R.layout.chips_fragment) {

    companion object {
        private const val IS_LIGHT = "IS_LIGHT"
    }

    private val binding by viewBinding(ChipsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sharePreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isLight = sharePreferences.getBoolean(IS_LIGHT, false)

        binding.themeGroup.check(if (isLight) R.id.lightThemeChip else R.id.darkThemeChip)

        binding.themeGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val isLightChecked = when (checkedId) {
                R.id.lightThemeChip -> true
                else -> false
            }
            sharePreferences.edit().putBoolean(IS_LIGHT, isLightChecked).apply()
            requireActivity().recreate()
        }
    }
}
