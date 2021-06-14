package com.headmostlab.materialdesignapp.repository

interface SettingsRepository {
    fun isLightTheme(): Boolean
    fun changeTheme(isLight: Boolean)
}
