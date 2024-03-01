package com.raven.home.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.raven.home.domain.models.MediaMetaData
import com.raven.home.presentation.Utils.Companion.NEWS_TABLE_NAME


@Entity(tableName = NEWS_TABLE_NAME)
data class NewsModelDB(
    @PrimaryKey(autoGenerate = true)
    val num:Int =0,
    @SerializedName("title")
    @ColumnInfo("title")
    val titulo:String?  ="",
    @ColumnInfo("autor")
    @SerializedName("autor")
    val autor:String? ="",
    @ColumnInfo("contenido")
    @SerializedName("contenido")
    val contenido: String? ="",
    @ColumnInfo("date")
    @SerializedName("date")
    val date: String?  ="",
    @ColumnInfo("url")
    @SerializedName("url")
    val url: String? =""

)
