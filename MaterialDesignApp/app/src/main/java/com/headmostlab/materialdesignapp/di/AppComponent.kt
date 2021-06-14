package com.headmostlab.materialdesignapp.di

import android.content.Context
import com.headmostlab.materialdesignapp.data.datasource.PODRetrofitImpl
import com.headmostlab.materialdesignapp.data.datasource.PictureOfTheDayApi
import com.headmostlab.materialdesignapp.data.repository.PictureOfTheDayRepositoryImpl
import com.headmostlab.materialdesignapp.data.repository.SettingsRepositoryImpl
import com.headmostlab.materialdesignapp.repository.PictureOfTheDayRepository
import com.headmostlab.materialdesignapp.repository.SettingsRepository
import com.headmostlab.materialdesignapp.ui.settings.SettingsScreenComponent
import dagger.*
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, Subcomponents::class])
interface AppComponent {

    fun getSettingsRepository(): SettingsRepository
    fun getPictureOfTheDayRepository(): PictureOfTheDayRepository
    fun getSettingsScreenComponentFactory(): SettingsScreenComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): AppComponent
    }
}

@Module
interface AppModule {

    @Binds
    fun providePictureOfTheDayRepository(repository: PictureOfTheDayRepositoryImpl): PictureOfTheDayRepository

    @Singleton
    @Binds
    fun provideSettingRepository(repository: SettingsRepositoryImpl): SettingsRepository

    companion object {
        @Singleton
        @Provides
        fun provideApi(): PictureOfTheDayApi = PODRetrofitImpl().getRetrofitImpl()
    }
}

@Module(subcomponents = [SettingsScreenComponent::class])
interface Subcomponents