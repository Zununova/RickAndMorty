package com.example.rickandmorty.base

import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> Response<T>) = flow {
        emit(Resource.Loading())
        try {
            request().also {
                if (it.isSuccessful && it.body() != null) {
                    emit(Resource.Success(it.body()!!))
                } else {
                    emit(Resource.Error(null, it.message()))
                }
            }
        } catch (exception: Exception) {
            emit(Resource.Error(null, exception.localizedMessage ?: "Error"))
        }
    }.flowOn(Dispatchers.IO)
}