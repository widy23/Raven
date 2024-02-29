package com.raven.home.data

import android.util.Log
import com.raven.home.data.remote.ServiceResult
import com.raven.home.data.remote.service.HomeService
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.models.NewsModel
import com.raven.home.domain.models.ResponseApiNews
import com.raven.home.presentation.Utils.Companion.API_KEY
import com.raven.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(private val service: HomeService) : HomeDataSource {

    private var period: Int = 1 // Valor predeterminado, puedes cambiarlo según necesites

    override suspend fun getNews(period:String): Flow<List<NewsModel>> {
        return flow<List<NewsModel>> {
            try {

                ServiceResult.createCall({service.getNews(period, API_KEY)
                }, {
                    // si entra por aquí, se carga primero la pase de datos

                    emit(it.results)
                }) {
                    emit(emptyList())
                }

            } catch (e: Exception) {
                // Manejar el error
                Log.e("NewsRepository", "Error fetching news", e)
                emit(emptyList())
            }
        }
    }

}
