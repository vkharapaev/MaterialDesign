package com.headmostlab.materialdesignapp.ui.settings

import androidx.lifecycle.ViewModel
import com.headmostlab.materialdesignapp.di.ScreenScope
import com.headmostlab.materialdesignapp.di.ViewModelFactory
import com.headmostlab.materialdesignapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@ScreenScope
@Subcomponent(modules = [SettingsScreenModule::class])
interface SettingsScreenComponent {
    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun build(): SettingsScreenComponent
    }
}

@Module
interface SettingsScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun provideViewModel(viewModel: SettingsViewModel): ViewModel
}