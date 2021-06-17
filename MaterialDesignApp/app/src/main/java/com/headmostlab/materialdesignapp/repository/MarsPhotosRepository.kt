package com.headmostlab.materialdesignapp.repository

import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto
import io.reactivex.Single

interface MarsPhotosRepository {
    fun getLatestPhotos(apiKey: String): Single<List<MarsPhoto>>
}