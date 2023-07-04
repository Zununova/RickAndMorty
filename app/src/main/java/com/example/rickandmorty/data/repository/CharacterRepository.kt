package com.example.rickandmorty.data.repository

import com.example.rickandmorty.base.BaseRepository
import com.example.rickandmorty.data.remote.apiservice.CharacterApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val service: CharacterApiService) :
    BaseRepository() {

    fun fetchCharacter() = doRequest {
        service.fetchCharacter()
    }
}