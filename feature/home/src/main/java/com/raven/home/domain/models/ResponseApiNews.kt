package com.raven.home.domain.models

import com.google.gson.annotations.SerializedName

data class ResponseApiNews (
    @SerializedName("results")
    val results: List<NewsModel>
)