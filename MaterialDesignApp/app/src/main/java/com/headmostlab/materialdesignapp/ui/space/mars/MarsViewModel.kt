package com.headmostlab.materialdesignapp.ui.space.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.headmostlab.findmovie.Event
import com.headmostlab.materialdesignapp.BuildConfig
import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto
import com.headmostlab.materialdesignapp.repository.MarsPhotosRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MarsViewModel @Inject constructor(private val marsPhotosRepository: MarsPhotosRepository) :
    ViewModel() {

    private val photosData = mutableListOf<MarsPhotoUi>()

    private val _photos: MutableLiveData<MarsPhotosState> = MutableLiveData()
    val photos: LiveData<MarsPhotosState>
        get() {
            getPhotos()
            return _photos
        }

    private val _onShowPicture: MutableLiveData<Event<MarsPhoto>> = MutableLiveData()
    val onShowPicture: LiveData<Event<MarsPhoto>> = _onShowPicture

    private val disposables: CompositeDisposable = CompositeDisposable()

    private fun getPhotos() {
        if (disposables.size() > 0) return

        _photos.value = MarsPhotosState.Loading

        marsPhotosRepository.getLatestPhotos(BuildConfig.NASA_API_KEY)
            .subscribe({ data ->
                val uiData = data.map { MarsPhotoUi(it, false) }
                photosData.apply {
                    clear()
                    addAll(uiData)
                }
                _photos.value = MarsPhotosState.Success(uiData)
            }, {
                _photos.value = MarsPhotosState.Error(it)
            }).also { disposables.add(it) }
    }

    fun selectPhoto(position: Int) {
        val success = _photos.value as? MarsPhotosState.Success
        success?.let {
            success.data.getOrNull(position)?.let {
                _onShowPicture.value = Event(it.photo)
            }
        }
    }

    fun removeItem(position: Int) {
        photosData.removeAt(position)
        notifyPhotosChanged()
    }

    private fun notifyPhotosChanged() {
        _photos.value =
            MarsPhotosState.Success(arrayListOf<MarsPhotoUi>().apply { addAll(photosData) })
    }

    fun move(fromPosition: Int, toPosition: Int) {
        photosData[fromPosition] = photosData[toPosition]
            .also { photosData[toPosition] = photosData[fromPosition] }
        notifyPhotosChanged()
    }

    fun showDescription(position: Int) {
        val old = photosData[position]

        photosData[position] = old.copy(isDescriptionVisible = !old.isDescriptionVisible)

        notifyPhotosChanged()
    }
}
