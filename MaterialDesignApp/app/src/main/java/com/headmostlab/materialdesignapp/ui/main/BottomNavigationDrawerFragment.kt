package com.headmostlab.materialdesignapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.BottomNavigationDrawerFragmentBinding
import com.headmostlab.materialdesignapp.ui.utils.viewBinding

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(BottomNavigationDrawerFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_drawer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_one -> Toast.makeText(
                    requireContext(),
                    "Navigation One",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.navigation_two -> Toast.makeText(
                    requireContext(),
                    "Navigation Two",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}