package com.headmostlab.materialdesignapp.ui.space.mars

import com.headmostlab.materialdesignapp.domain.entity.MarsPhoto

sealed class MarsPhotosState {
    data class Success(val data: List<MarsPhoto>) : MarsPhotosState()
    data class Error(val error: Throwable) : MarsPhotosState()
    object Loading : MarsPhotosState()
}