package com.headmostlab.materialdesignapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.headmostlab.materialdesignapp.BuildConfig
import com.headmostlab.materialdesignapp.repository.PictureOfTheDayRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PictureOfTheDayViewModel @Inject constructor(
    private val repository: PictureOfTheDayRepository
) : ViewModel() {

    private val liveDataForViewToObserver = MutableLiveData<PictureOfTheDayData>()
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun getData(date: String? = null): LiveData<PictureOfTheDayData> {
        disposables.clear()
        sendServerRequest(date)
        return liveDataForViewToObserver
    }

    private fun sendServerRequest(date: String? = null) {
        liveDataForViewToObserver.value = PictureOfTheDayData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveDataForViewToObserver.value =
                PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            repository.getPictureOfTheDay(apiKey, date).subscribe({
                liveDataForViewToObserver.value = PictureOfTheDayData.Success(it)
            }, {
                liveDataForViewToObserver.value = PictureOfTheDayData.Error(it)
            }).also { disposables.add(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}