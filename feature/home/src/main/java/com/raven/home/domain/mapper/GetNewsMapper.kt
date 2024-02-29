package com.raven.home.domain.mapper

import com.raven.home.domain.models.ResponseApiNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsMapper @Inject constructor() {

    suspend fun transformDomainToUI(params: @JvmSuppressWildcards List<ResponseApiNews>) {
        //TODO Aquí transforma los objetos de dominio en objetos de IU y devuelve la lista resultante
    }
}
