package com.headmostlab.materialdesignapp.di

import android.content.Context
import com.headmostlab.materialdesignapp.data.datasource.PODRetrofitImpl
import com.headmostlab.materialdesignapp.data.datasource.PictureOfTheDayApi
import com.headmostlab.materialdesignapp.data.repository.PictureOfTheDayRepositoryImpl
import com.headmostlab.materialdesignapp.repository.PictureOfTheDayRepository
import dagger.*
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getPictureOfTheDayRepository(): PictureOfTheDayRepository

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

    companion object {
        @Singleton
        @Provides
        fun provideApi(): PictureOfTheDayApi = PODRetrofitImpl().getRetrofitImpl()
    }
}