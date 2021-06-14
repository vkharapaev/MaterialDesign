package com.headmostlab.materialdesignapp.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.headmostlab.findmovie.Event
import com.headmostlab.materialdesignapp.repository.SettingsRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val settings: SettingsRepository) :
    ViewModel() {

    private val _isLightTheme: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isLightTheme: LiveData<Event<Boolean>> = _isLightTheme

    fun setTheme(isLight: Boolean) {
        if (settings.isLightTheme() != isLight) {
            settings.changeTheme(isLight)
            _isLightTheme.value = Event(isLight)
        }
    }
}