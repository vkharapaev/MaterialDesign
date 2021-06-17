package com.headmostlab.materialdesignapp.data.datasource.marsphotos

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPhotosApi {
    @GET("mars-photos/api/v1/rovers/curiosity/latest_photos")
    fun getLatestPhotos(@Query("api_key") apiKey: String): Single<MarsPhotosDTO>
}
