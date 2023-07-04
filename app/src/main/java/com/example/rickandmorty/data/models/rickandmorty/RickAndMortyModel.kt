package com.example.rickandmorty.data.models.rickandmorty

import com.example.rickandmorty.data.models.character.CharacterModel
import com.example.rickandmorty.data.models.location.LocationModel

data class RickAndMortyModel(
    val characterModel: CharacterModel,
    val locationModel: LocationModel,
    val id: Int = 0
)

