package com.headmostlab.materialdesignapp.data.datasource

import com.google.gson.annotations.SerializedName

data class PODServerResponseData(
    @field:SerializedName("copyright") val copyright: String?,
    @field:SerializedName("data") val data: String?,
    @field:SerializedName("explanation") val explanation: String?,
    @field:SerializedName("media_type") val media_type: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("hdurl") val hdurl: String?,
)
