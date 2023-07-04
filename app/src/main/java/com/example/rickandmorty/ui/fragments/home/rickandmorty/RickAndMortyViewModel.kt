package com.example.rickandmorty.ui.fragments.home.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.models.rickandmorty.RickAndMortyModel
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.data.repository.LocationRepository
import com.example.rickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository
) : BaseViewModel() {
    private val _noteLiveData =
        MutableLiveData<Resource<List<RickAndMortyModel>>>(Resource.Loading())
    val noteLiveData: LiveData<Resource<List<RickAndMortyModel>>> = _noteLiveData

    init {
        getData()
    }

    private fun getData() {
        val character = viewModelScope.async {
            characterRepository.fetchCharacter()
        }
        val location = viewModelScope.async {
            locationRepository.fetchLocation()
        }
        viewModelScope.launch {
            character.await().combine(location.await()) { character, location ->
                Pair(character, location)
            }.collect {
                when {
                    it.first is Resource.Error && it.second is Resource.Error -> {
                        _noteLiveData.value = Resource.Error(
                            message = it.first.message + it.second.message,
                            data = null
                        )
                    }
                    it.first is Resource.Success && it.second is Resource.Success -> {
                        val modelsList = mutableListOf<RickAndMortyModel>()
                        it.first.data!!.results.zip(it.second.data!!.results).forEach { models ->
                            modelsList.add(
                                RickAndMortyModel(
                                    models.first,
                                    models.second,
                                    models.first.id
                                )
                            )
                        }
                        _noteLiveData.value = Resource.Success(modelsList)
                    }
                }
            }
        }
    }
}