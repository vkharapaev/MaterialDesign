package com.headmostlab.materialdesignapp.data.repository

import com.headmostlab.materialdesignapp.data.datasource.marsphotos.MarsPhotosApi
import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto
import com.headmostlab.materialdesignapp.repository.MarsPhotosRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MarsPhotosRepositoryImpl @Inject constructor(
    private val marsPhotosApi: MarsPhotosApi
) : MarsPhotosRepository {

    override fun getLatestPhotos(apiKey: String): Single<List<MarsPhoto>> =
        marsPhotosApi.getLatestPhotos(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toListOfMarsPhoto() }
}