package com.headmostlab.materialdesignapp.ui.chips

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.ChipsFragmentBinding
import com.headmostlab.materialdesignapp.ui.utils.viewBinding

class ChipsFragment : Fragment(R.layout.chips_fragment) {

    private val binding by viewBinding(ChipsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.chipGroup2.setOnCheckedChangeListener { chipGroup, checkedId ->
            binding.chipGroup2.findViewById<Chip>(checkedId)?.let {
                Toast.makeText(requireContext(), "Chosen ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }
        binding.chipClose.setOnCloseIconClickListener {
            Toast.makeText(requireContext(), "Close is clicked", Toast.LENGTH_SHORT).show()
        }
        binding.chipClose.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(requireContext(), "isChecked = $isChecked", Toast.LENGTH_SHORT).show()
        }
    }
}
