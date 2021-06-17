package com.headmostlab.materialdesignapp.ui.main

import com.headmostlab.materialdesignapp.domain.entity.PictureOfTheDay

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: PictureOfTheDay) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}
