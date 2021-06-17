package com.headmostlab.materialdesignapp.data.datasource.pod

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.headmostlab.materialdesignapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

class PODRetrofitImpl @Inject constructor(
    private val client: OkHttpClient,
    private val gson: Gson
) {
    private val baseUrl = "https://api.nasa.gov/"

    fun getRetrofitImpl(): PictureOfTheDayApi {
        val podRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return podRetrofit.create(PictureOfTheDayApi::class.java)
    }
}
