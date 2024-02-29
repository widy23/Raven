package com.raven.home.data.remote

import retrofit2.Response



    data class ServiceResult<out T>(val status: Status, val data: T?, val error: String?) {

        enum class Status {
            SUCCESS,
            ERROR,
            LOADING;

            fun isSuccess() = this == SUCCESS
        }

        companion object {

            suspend fun <T : Any> createCall(
                call: suspend () -> Response<T>, onSuccess:suspend (T) -> Unit,
                onError:suspend (String) -> Unit
            ) {
                try {
                    val response = call()

                    if (response.isSuccessful && response.body() != null) {
                        onSuccess(response.body()!!)
                    } else {
                        val error = "ERROR"
                        onError(error)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("ERROR")
                }
            }
        }

    }

