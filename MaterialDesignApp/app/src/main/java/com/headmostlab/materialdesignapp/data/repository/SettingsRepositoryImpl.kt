package com.headmostlab.materialdesignapp.data.repository

import android.content.Context
import androidx.preference.PreferenceManager
import com.headmostlab.materialdesignapp.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(context: Context) : SettingsRepository {

    companion object {
        private const val IS_LIGHT_THEME = "IS_LIGHT"
    }

    private val sharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun isLightTheme(): Boolean = sharedPreferences.getBoolean(IS_LIGHT_THEME, false)

    override fun changeTheme(isLight: Boolean) =
        sharedPreferences.edit().putBoolean(IS_LIGHT_THEME, isLight).apply()
}