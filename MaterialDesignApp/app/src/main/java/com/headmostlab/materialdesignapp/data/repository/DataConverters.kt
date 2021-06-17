package com.headmostlab.materialdesignapp.data.repository

import com.headmostlab.materialdesignapp.data.datasource.marsphotos.MarsPhotosDTO
import com.headmostlab.materialdesignapp.data.datasource.pod.PictureOfTheDayDTO
import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto
import com.headmostlab.materialdesignapp.domain.entity.PictureOfTheDay

fun PictureOfTheDayDTO.toPictureOfTheDay(): PictureOfTheDay =
    PictureOfTheDay(copyright, data, explanation, media_type, title, url, hdurl)

fun MarsPhotosDTO.toListOfMarsPhoto(): List<MarsPhoto> =
    latestPhotos.map { MarsPhoto(it.id, it.earthDate, it.imgSrc) }
