package com.headmostlab.materialdesignapp.data.repository

import com.headmostlab.materialdesignapp.data.datasource.PODServerResponseData
import com.headmostlab.materialdesignapp.data.datasource.PictureOfTheDayApi
import com.headmostlab.materialdesignapp.repository.PictureOfTheDayRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PictureOfTheDayRepositoryImpl @Inject constructor(private val api: PictureOfTheDayApi) :
    PictureOfTheDayRepository {

    override fun getPictureOfTheDay(apiKey: String, date: String?): Single<PODServerResponseData> =
        api.getPictureOfTheDay(apiKey, date).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
