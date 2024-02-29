package com.raven.home.domain.models

import com.google.gson.annotations.SerializedName

data class MediaMetaData(
@SerializedName("media-metadata")
val metaData :List<MediaClass>
)

