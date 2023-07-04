package com.example.rickandmorty.data.remote.apiservice

import com.example.rickandmorty.data.models.RickAndMortyResponse
import com.example.rickandmorty.data.models.character.CharacterModel
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {

    @GET("api/character")
    suspend fun fetchCharacter(): Response<RickAndMortyResponse<CharacterModel>>
}