package com.headmostlab.materialdesignapp.repository

import com.headmostlab.materialdesignapp.data.datasource.PODServerResponseData
import io.reactivex.Single
import retrofit2.http.Query

interface PictureOfTheDayRepository {
    fun getPictureOfTheDay(apiKey: String, date: String? = null): Single<PODServerResponseData>
}