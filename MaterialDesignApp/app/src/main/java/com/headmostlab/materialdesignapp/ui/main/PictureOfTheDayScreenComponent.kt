package com.headmostlab.materialdesignapp.ui.main

import androidx.lifecycle.ViewModel
import com.headmostlab.materialdesignapp.di.ScreenScope
import com.headmostlab.materialdesignapp.di.ViewModelFactory
import com.headmostlab.materialdesignapp.di.ViewModelKey
import com.headmostlab.materialdesignapp.repository.PictureOfTheDayRepository
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@ScreenScope
@Component(modules = [PictureOfTheDayScreenModule::class])
interface PictureOfTheDayScreenComponent {
    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setPictureOfTheDayRepository(repository: PictureOfTheDayRepository): Builder
        fun build(): PictureOfTheDayScreenComponent
    }
}

@Module
interface PictureOfTheDayScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(PictureOfTheDayViewModel::class)
    fun provideViewModel(viewModel: PictureOfTheDayViewModel): ViewModel
}
