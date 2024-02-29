package com.raven.home.data.remote.service

import com.raven.home.domain.models.ResponseApiNews
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface HomeService {

    //TODO("Correctly apply the Path and its answers. The API Key is provided in your PDF document")

    @GET("svc/mostpopular/v2/emailed/7.json?")
    suspend fun getNews(@Query("api-key") apiKey: String): Response<ResponseApiNews>
}
