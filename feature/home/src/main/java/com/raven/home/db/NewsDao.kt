package com.raven.home.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raven.home.domain.models.NewsModel

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAllNews(): List<NewsModelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(channel: List< NewsModelDB>)
}