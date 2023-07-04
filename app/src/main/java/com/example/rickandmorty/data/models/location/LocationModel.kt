package com.example.rickandmorty.data.models.location

import com.google.gson.annotations.SerializedName

data class LocationModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val locationName: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("dimension")
    val dimension: String
)
