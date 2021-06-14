package com.headmostlab.materialdesignapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.headmostlab.materialdesignapp.R

class MainActivity : AppCompatActivity() {

    companion object {
        private const val IS_LIGHT = "IS_LIGHT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharePreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isLight = sharePreferences.getBoolean(IS_LIGHT, false)
        setTheme(if (isLight) R.style.Theme_Light else R.style.Theme_Dark)
        setContentView(R.layout.main_activity)
    }

}