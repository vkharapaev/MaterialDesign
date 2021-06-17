package com.headmostlab.materialdesignapp.data.datasource.marsphotos

import com.google.gson.annotations.SerializedName

data class MarsPhotoDTO(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("earth_date") val earthDate: String,
    @field:SerializedName("img_src") val imgSrc: String
)
