package com.headmostlab.materialdesignapp.ui.space.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.headmostlab.materialdesignapp.BuildConfig
import com.headmostlab.materialdesignapp.repository.MarsPhotosRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MarsViewModel @Inject constructor(private val marsPhotosRepository: MarsPhotosRepository) :
    ViewModel() {

    private val _photos: MutableLiveData<MarsPhotosState> = MutableLiveData()

    private val disposables: CompositeDisposable = CompositeDisposable()

    val photos: LiveData<MarsPhotosState>
        get() {
            getPhotos()
            return _photos
        }

    private fun getPhotos() {
        _photos.value = MarsPhotosState.Loading

        if (disposables.size() > 0) return

        marsPhotosRepository.getLatestPhotos(BuildConfig.NASA_API_KEY)
            .subscribe({
                _photos.value = MarsPhotosState.Success(it)
            }, {
                _photos.value = MarsPhotosState.Error(it)
            }).also { disposables.add(it) }
    }

}