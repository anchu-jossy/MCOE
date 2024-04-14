package com.example.mcoeexercise

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.mcoeexercise.network.Result
import com.example.mcoeexercise.utils.getIdFromUrl
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class MainViewModel @Inject constructor(val repo: PlanetRepository) : ViewModel() {
    private val _progressFlow = MutableStateFlow<Boolean>(false)
    val progressFlow: StateFlow<Boolean> = _progressFlow

    private val _planetDetail: MutableState<Planet?> = mutableStateOf(null)
    val planetDetail: MutableState<Planet?> = _planetDetail

    private val _planetList: MutableState<BaseResponse<Planet>?> = mutableStateOf(null)
    val planetList:MutableState<BaseResponse<Planet>?> = _planetList

    private val _planetListFlow = emptyFlow<PagingData<Planet>>()
    var planetListFlow: Flow<PagingData<Planet>> = _planetListFlow

    private val _filmList: MutableState<List<Film>?> = mutableStateOf(null)
    val filmList:MutableState<List<Film>?> = _filmList



   /* fun getPlanets()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repo.planetList().collectLatest{ pagingData ->

                Log.e("", " " )

            }



//            _planetListFlow.value =
            *//*_planetListFlow.flatMapLatest {
                repo.planetList()
            }.collect{
                it.map {
                    Log.e("Result", "Res "  )

                }
                *//**//*when(it){
                    Log.e("Result", "Res "  )
                    is Result.Success<*> -> {
                        Log.e("Result", "Res "  )
                    }
                }*//**//*
            }*//*
        }
    }
*/
    fun getPlanets()
    {
        viewModelScope.launch(Dispatchers.IO) {

            repo.planets().collect{
                Log.e("Result", "Res "  )
                when(it){
                    is Result.Loading -> {
                        _progressFlow.value = true
                    }
                    is Result.Success<BaseResponse<Planet>> -> {
                        _progressFlow.value = false
                        _planetList.value = it.data
                    }

                    is Result.Error -> {
                        Log.e("Result", "Res "  )
                        _progressFlow.value = false
                    }
                }
            }

        }
    }

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


}