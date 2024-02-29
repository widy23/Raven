package com.raven.home.domain.usecases

import com.raven.core.bases.BaseUseCase
import com.raven.home.data.HomeRepository
import com.raven.home.domain.mapper.GetNewsMapper
import com.raven.home.domain.models.NewsModel
import com.raven.home.domain.models.ResponseApiNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeNewsUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val mapper: GetNewsMapper
) : BaseUseCase<String, List<NewsModel>>() {


    override suspend fun execute(params: String): Flow<List<NewsModel>> {
        return repository.getNews(params)
    }
}
