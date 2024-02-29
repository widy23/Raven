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
            fun <T> success(data: T): ServiceResult<T> {
                return ServiceResult(Status.SUCCESS, data, null)
            }

            fun <T> successEmpty(): ServiceResult<T> {
                return ServiceResult(Status.SUCCESS, null, null)
            }

            fun <T> error(data: T? = null, error: String? = null): ServiceResult<T> {
                return ServiceResult(Status.ERROR, data, error)
            }

            fun <T> loading(data: T? = null): ServiceResult<T> {
                return ServiceResult(Status.LOADING, data, null)
            }


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

