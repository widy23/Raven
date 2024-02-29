package com.raven.home.domain

import com.raven.home.domain.models.NewsModel
import com.raven.home.domain.models.ResponseApiNews
import kotlinx.coroutines.flow.Flow


interface HomeDataSource {

    suspend fun getNews(period:String): Flow<List<NewsModel>>
}
