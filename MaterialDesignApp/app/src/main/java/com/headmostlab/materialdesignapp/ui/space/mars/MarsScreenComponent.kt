package com.headmostlab.materialdesignapp.ui.space.mars

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.headmostlab.materialdesignapp.data.datasource.marsphotos.MarsPhotosApi
import com.headmostlab.materialdesignapp.data.datasource.marsphotos.MarsPhotosRetrofitImpl
import com.headmostlab.materialdesignapp.data.repository.MarsPhotosRepositoryImpl
import com.headmostlab.materialdesignapp.di.ScreenScope
import com.headmostlab.materialdesignapp.di.ViewModelFactory
import com.headmostlab.materialdesignapp.di.ViewModelKey
import com.headmostlab.materialdesignapp.repository.MarsPhotosRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import javax.inject.Singleton

@ScreenScope
@Subcomponent(modules = [MarsScreenModule::class])
interface MarsScreenComponent {
    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun build(): MarsScreenComponent
    }
}

@Module
interface MarsScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(MarsViewModel::class)
    fun provideViewModel(viewModel: MarsViewModel): ViewModel

    @Binds
    fun provideMarsPhotosRepository(repository: MarsPhotosRepositoryImpl): MarsPhotosRepository

    companion object {

        @ScreenScope
        @Provides
        fun provideMarsPhotosApi(client: OkHttpClient, gson: Gson): MarsPhotosApi =
            MarsPhotosRetrofitImpl(client, gson).getRetrofitImpl()
    }
}
