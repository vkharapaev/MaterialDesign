package com.headmostlab.materialdesignapp.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.headmostlab.materialdesignapp.DI
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.SettingsFragmentBinding
import com.headmostlab.materialdesignapp.ui.utils.viewBinding

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private val component by lazy { DI.appComponent.getSettingsScreenComponentFactory().build() }

    private val binding by viewBinding(SettingsFragmentBinding::bind)

    private val viewModel by viewModels<SettingsViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.themeGroup.check(
            if (DI.appComponent.getSettingsRepository().isLightTheme())
                R.id.lightThemeChip else R.id.darkThemeChip
        )

        binding.themeGroup.setOnCheckedChangeListener { _, checkedId ->
            viewModel.setTheme(checkedId == R.id.lightThemeChip)
        }

        viewModel.isLightTheme.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                requireActivity().recreate()
            }
        }
    }
}
