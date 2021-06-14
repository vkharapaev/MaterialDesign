package com.headmostlab.materialdesignapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.headmostlab.materialdesignapp.DI
import com.headmostlab.materialdesignapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLightTheme = DI.appComponent.getSettingsRepository().isLightTheme()
        setTheme(if (isLightTheme) R.style.Theme_Light else R.style.Theme_Dark)
        setContentView(R.layout.main_activity)
    }

}