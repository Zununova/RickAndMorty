package com.example.rickandmorty.di

import com.example.rickandmorty.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient() = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharacterApiService(retrofitClient: RetrofitClient) =
        retrofitClient.provideCharacterApiService()

    @Singleton
    @Provides
    fun provideLocationApiService(retrofitClient: RetrofitClient) =
        retrofitClient.provideLocationApiService()
}