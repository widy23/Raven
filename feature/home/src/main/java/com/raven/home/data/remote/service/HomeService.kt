package com.raven.home.data.remote.service

import com.raven.home.domain.models.ResponseApiNews
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface HomeService {

    //TODO("Correctly apply the Path and its answers. The API Key is provided in your PDF document")
    //https://api.nytimes.com/svc/mostpopular/v2/emailed/1.json?api-key=V4Me99XOuOwdxrTCwsOR10HhqWOlRdLO


//    @GET("svc/mostpopular/v2/emailed/{period}.json?")
//    suspend fun getNews(@Path("") period :Int,
//                        @Query("api-key") apiKey: String): Response<ResponseApiNews>
    @GET("svc/mostpopular/v2/emailed/{period}.json")
    suspend fun getNews(@Path ("period") period:String,
                        @Query("api-key") apiKey: String): Response<ResponseApiNews>
}
