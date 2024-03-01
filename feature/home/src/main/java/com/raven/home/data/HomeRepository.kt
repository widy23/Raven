package com.raven.home.data

import android.util.Log
import com.raven.home.data.remote.ServiceResult
import com.raven.home.data.remote.service.HomeService
import com.raven.home.db.NewsDao
import com.raven.home.db.NewsModelDB
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.models.ResponseApiNews
import com.raven.home.presentation.Utils.Companion.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val service: HomeService,
    private val newsDao: NewsDao
) : HomeDataSource {

    override suspend fun getNews(period: String): Flow<ServiceResult<List<NewsModelDB>>> {
        return flow {
            try {
                ServiceResult.createCall({ service.getNews(period, API_KEY) },
                    {
                        if (it.results.isNotEmpty()) {
                            emit(ServiceResult.success(dataToView(it)))
                        }
                    }) {
                    emit(ServiceResult.error(error=it))

                    Log.d("TAG", "getNewsError: $it")
                }
            } catch (e: Exception) {
                Log.e("NewsRepository", "Error fetching news", e)
                emit(ServiceResult.error())
            }
        }
    }

    //Metodo que llama a DDBB
     fun newsFromDb(): List<NewsModelDB> {
        return newsDao.getAllNews()
    }

    private fun dataToView(response: ResponseApiNews):List<NewsModelDB>{
        var listToDb = mutableListOf<NewsModelDB>()
        for(item in response.results){
            val title = item.titulo
            val autor = item.autor
            val contenido = item.contenido
            val date = item.date
            val url = item.media?.lastOrNull()?.metaData?.lastOrNull()?.urlImage

            listToDb.add(NewsModelDB(titulo = title, autor = autor, contenido = contenido, date = date, url = url))
        }
        saveDataToDB(listToDb)

        return listToDb
    }

    private fun saveDataToDB(response: MutableList<NewsModelDB>) {
        val dataFromDb = newsFromDb()
        if (dataFromDb.isEmpty()) newsDao.insertNews(response)

    }

}