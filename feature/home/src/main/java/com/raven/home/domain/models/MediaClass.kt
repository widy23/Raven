package com.raven.home.domain.models

import com.google.gson.annotations.SerializedName

data class MediaClass(
    @SerializedName("url")
    val urlImage:String?,
    @SerializedName("width")
    val width :Int?,
)
