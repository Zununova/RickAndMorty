package com.example.rickandmorty.data.repository

import com.example.rickandmorty.base.BaseRepository
import com.example.rickandmorty.data.remote.apiservice.LocationApiService
import javax.inject.Inject

class LocationRepository @Inject constructor(private val service: LocationApiService) :
    BaseRepository() {

    fun fetchLocation() = doRequest {
        service.fetchLocation()
    }
}