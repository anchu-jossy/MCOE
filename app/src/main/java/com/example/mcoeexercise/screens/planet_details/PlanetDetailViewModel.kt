package com.example.mcoeexercise.screens.planet_details

// Import statements for necessary classes and interfaces
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

/**
 * ViewModel for the PlanetDetail screen.
 *
 * @param repo The repository for fetching data related to planet details.
 */
@HiltViewModel
class PlanetDetailViewModel @Inject constructor(private val repo: PlanetRepository) : ViewModel() {
    // MutableStateFlow to track progress state
    private val _progressFlow = MutableStateFlow<Boolean>(false)
    val progressFlow: StateFlow<Boolean> = _progressFlow

    // MutableState to hold planet details
    private val _planetDetail: MutableState<Planet?> = mutableStateOf(null)
    val planetDetail: MutableState<Planet?> = _planetDetail

    // MutableState to hold film list
    private val _filmList: MutableState<List<Film>?> = mutableStateOf(null)
    val filmList: MutableState<List<Film>?> = _filmList

    // MutableState to hold people list
    private val _peopleList: MutableState<List<People>?> = mutableStateOf(null)
    val peopleList: MutableState<List<People>?> = _peopleList

    /**
     * Function to fetch planet details from the repository.
     *
     * @param planetId The ID of the planet.
     */
    fun planetDetails(planetId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.planetDetail(planetId).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _progressFlow.value = true
                    }
                    is Result.Success<Planet> -> {
                        _planetDetail.value = result.data
                        _progressFlow.value = false
                    }
                    is Result.Error -> {
                        _progressFlow.value = false
                    }
                }
            }
        }
    }

    /**
     * Function to fetch film details for the given list of film URLs.
     *
     * @param films List of film URLs associated with the planet.
     */
    fun planetFilms(films: ArrayList<String>) {
        val filmList = mutableListOf<Film>()
        viewModelScope.launch(Dispatchers.IO) {
            films.map {
                async {
                    repo.film(getIdFromUrl(it))
                }
            }.awaitAll().forEach { result ->
                result.collect { filmResult ->
                    if (filmResult is Result.Success<Film>) {
                        filmList.add(filmResult.data)
                    }
                }
            }
            _filmList.value = filmList
        }
    }

    /**
     * Function to fetch details of people associated with the given list of people URLs.
     *
     * @param peopleList List of people URLs associated with the planet.
     */
    fun planetPeople(peopleList: ArrayList<String>) {
        val peopleLists = mutableListOf<People>()
        viewModelScope.launch(Dispatchers.IO) {
            peopleList.map {
                async {
                    repo.people(getIdFromUrl(it))
                }
            }.awaitAll().forEach { result ->
                result.collect { peopleResult ->
                    if (peopleResult is Result.Success<People>) {
                        peopleLists.add(peopleResult.data)
                    }
                }
            }
            _peopleList.value = peopleLists
        }
    }
}
