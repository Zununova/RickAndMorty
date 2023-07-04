package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.data.models.RickAndMortyResponse
import com.example.rickandmorty.data.models.location.LocationModel
import retrofit2.Response
import retrofit2.http.GET

interface LocationApiService {

    @GET("api/location")
    suspend fun fetchLocation(): Response<RickAndMortyResponse<LocationModel>>
}