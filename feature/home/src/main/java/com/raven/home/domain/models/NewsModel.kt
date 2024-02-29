package com.raven.home.domain.models

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("title")
    val titulo:String,
    @SerializedName("byline")
    val autor:String,
    @SerializedName("adx_keywords")
    val contenido: String,
    @SerializedName("published_date")
    val date: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("media")
    val media: List<MediaMetaData>

)

