package com.headmostlab.materialdesignapp.ui.space

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.headmostlab.materialdesignapp.R
import com.headmostlab.materialdesignapp.databinding.SpaceViewFragmentBinding
import com.headmostlab.materialdesignapp.ui.space.earth.EarthFragment
import com.headmostlab.materialdesignapp.ui.space.mars.MarsFragment
import com.headmostlab.materialdesignapp.ui.space.system.SystemFragment
import com.headmostlab.materialdesignapp.ui.utils.viewBinding

class SpaceViewFragment : Fragment(R.layout.space_view_fragment) {

    private val binding by viewBinding(SpaceViewFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menu = binding.bottomNavigation.menu

        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = ViewPagerAdapter(this, menu)
        binding.circleIndicator.setViewPager(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.selectedItemId = menu[position].itemId
            }
        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            for (position in 0 until menu.size()) {
                if (menu[position] == it) {
                    binding.viewPager.currentItem = position
                }
            }
            true
        }
    }

    private inner class ViewPagerAdapter(fragment: Fragment, private val menu: Menu) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return menu.size()
        }

        override fun createFragment(position: Int): Fragment =
            when (menu[position].itemId) {
                R.id.earth_item -> EarthFragment()
                R.id.mars_item -> MarsFragment()
                R.id.system_item -> SystemFragment()
                else -> throw IllegalArgumentException("Not supported screen")
            }
    }
}
