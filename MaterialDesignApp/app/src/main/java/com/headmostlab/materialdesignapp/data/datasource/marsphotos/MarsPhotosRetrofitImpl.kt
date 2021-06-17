package com.headmostlab.materialdesignapp.data.datasource.marsphotos

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MarsPhotosRetrofitImpl @Inject constructor(
    private val client: OkHttpClient,
    private val gson: Gson
) {
    private val baseUrl = "https://api.nasa.gov/"

    fun getRetrofitImpl(): MarsPhotosApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit.create(MarsPhotosApi::class.java)
    }
}