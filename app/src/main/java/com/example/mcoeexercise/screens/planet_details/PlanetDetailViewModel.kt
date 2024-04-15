package com.example.mcoeexercise.screens.planet_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.Result
import com.example.mcoeexercise.repository.PlanetRepository
import com.example.mcoeexercise.utils.getIdFromUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(private val repo: PlanetRepository) : ViewModel(){
    private val _progressFlow = MutableStateFlow<Boolean>(false)
    val progressFlow: StateFlow<Boolean> = _progressFlow

    private val _planetDetail: MutableState<Planet?> = mutableStateOf(null)
    val planetDetail: MutableState<Planet?> = _planetDetail

    private val _filmList: MutableState<List<Film>?> = mutableStateOf(null)
    val filmList: MutableState<List<Film>?> = _filmList

    private val _peopleList: MutableState<List<People>?> = mutableStateOf(null)
    val peopleList: MutableState<List<People>?> = _peopleList


    fun planetDetails(planetId: Int)
    {
        viewModelScope.launch(Dispatchers.IO) {

            repo.planetDetail(planetId).collect{
                when(it){
                    is Result.Loading -> {
                        _progressFlow.value = true
                    }
                    is Result.Success<Planet> -> {
                        _planetDetail.value = it.data
                        _progressFlow.value = false
                    }

                    is Result.Error -> {
                        _progressFlow.value = false
                    }
                }
            }

        }
    }

    fun planetFilms(films: ArrayList<String>) {
        val filmList = mutableListOf<Film>()
        viewModelScope.launch(Dispatchers.IO) {
            films.map {
                async {
                    repo.film(getIdFromUrl(it))
                }
            }.awaitAll().forEach {
                it.collect { film ->
                    if (film is Result.Success<Film>) {
                        filmList.add(film.data)
                    }
                }
            }
            _filmList.value = filmList
        }
    }

    fun planetPeople(peopleList: ArrayList<String>) {
        val peopleLists = mutableListOf<People>()
        viewModelScope.launch(Dispatchers.IO) {
            peopleList.map {
                async {
                    repo.people(getIdFromUrl(it))
                }
            }.awaitAll().forEach {
                it.collect { people ->
                    if (people is Result.Success<People>) {
                        peopleLists.add(people.data)
                    }
                }
            }
            _peopleList.value = peopleLists
        }
    }

}