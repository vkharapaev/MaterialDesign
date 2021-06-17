package com.headmostlab.materialdesignapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.headmostlab.materialdesignapp.BuildConfig
import com.headmostlab.materialdesignapp.data.datasource.pod.PODRetrofitImpl
import com.headmostlab.materialdesignapp.data.datasource.pod.PictureOfTheDayApi
import com.headmostlab.materialdesignapp.data.repository.MarsPhotosRepositoryImpl
import com.headmostlab.materialdesignapp.data.repository.PictureOfTheDayRepositoryImpl
import com.headmostlab.materialdesignapp.data.repository.SettingsRepositoryImpl
import com.headmostlab.materialdesignapp.repository.MarsPhotosRepository
import com.headmostlab.materialdesignapp.repository.PictureOfTheDayRepository
import com.headmostlab.materialdesignapp.repository.SettingsRepository
import com.headmostlab.materialdesignapp.ui.settings.SettingsScreenComponent
import com.headmostlab.materialdesignapp.ui.space.mars.MarsScreenComponent
import dagger.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, Subcomponents::class])
interface AppComponent {

    fun getSettingsRepository(): SettingsRepository
    fun getPictureOfTheDayRepository(): PictureOfTheDayRepository

    // subcomponents
    fun getSettingsScreenComponentFactory(): SettingsScreenComponent.Factory
    fun getMarsScreenComponentFactory(): MarsScreenComponent.Factory

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
        fun provideGson(): Gson = GsonBuilder().setLenient().create()

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
            }
            return httpClient.build()
        }

        @Singleton
        @Provides
        fun providePictureOfTheDayApi(client: OkHttpClient, gson: Gson): PictureOfTheDayApi =
            PODRetrofitImpl(client, gson).getRetrofitImpl()

    }
}

@Module(subcomponents = [SettingsScreenComponent::class, MarsScreenComponent::class])
interface Subcomponents