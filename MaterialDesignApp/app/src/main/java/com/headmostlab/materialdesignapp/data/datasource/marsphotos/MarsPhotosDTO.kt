package com.headmostlab.materialdesignapp.data.datasource.marsphotos

import com.google.gson.annotations.SerializedName

data class MarsPhotosDTO(
    @field:SerializedName("latest_photos") val latestPhotos: List<MarsPhotoDTO>
)