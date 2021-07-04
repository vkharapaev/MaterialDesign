package com.headmostlab.materialdesignapp.ui.space.mars

sealed class MarsPhotosState {
    data class Success(val data: List<MarsPhotoUi>) : MarsPhotosState()
    data class Error(val error: Throwable) : MarsPhotosState()
    object Loading : MarsPhotosState()
}