package com.raaceinm.androidpracticals.dataparse

import com.google.gson.annotations.SerializedName

data class DogApiResponse(
    @SerializedName("fileSizeBytes")
    val fileSizeBytes: Long,

    @SerializedName("url")
    val url: String
)