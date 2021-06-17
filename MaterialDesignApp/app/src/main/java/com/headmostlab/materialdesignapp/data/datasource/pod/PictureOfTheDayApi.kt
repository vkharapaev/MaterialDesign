package com.headmostlab.materialdesignapp.data.datasource.pod

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayApi {

    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String? = null
    ): Single<PictureOfTheDayDTO>
}
