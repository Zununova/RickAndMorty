package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.remote.apiservice.CharacterApiService
import com.example.rickandmorty.data.remote.apiservice.LocationApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofitClient = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun provideCharacterApiService(): CharacterApiService {
        return retrofitClient.create(CharacterApiService::class.java)
    }

    fun provideLocationApiService(): LocationApiService {
        return retrofitClient.create(LocationApiService::class.java)
    }
}