package com.headmostlab.materialdesignapp.repository

import com.headmostlab.materialdesignapp.domain.entity.PictureOfTheDay
import io.reactivex.Single

interface PictureOfTheDayRepository {
    fun getPictureOfTheDay(apiKey: String, date: String? = null): Single<PictureOfTheDay>
}