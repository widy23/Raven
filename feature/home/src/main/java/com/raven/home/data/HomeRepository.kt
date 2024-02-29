package com.raven.home.data

import com.raven.home.data.remote.ServiceResult
import com.raven.home.data.remote.service.HomeService
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.models.NewsModel
import com.raven.home.domain.models.ResponseApiNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val service: HomeService) : HomeDataSource {

    override suspend fun getNews(): Flow<List<NewsModel>> = flow<List<NewsModel>> {

        ServiceResult.createCall({
         service.getNews("V4Me99XOuOwdxrTCwsOR10HhqWOlRdLO")
        },{
            // si entra por ahi , se carga primero la pase de datos
        emit(it.results)
        },{
            emit(emptyList())
        })
    }


}
