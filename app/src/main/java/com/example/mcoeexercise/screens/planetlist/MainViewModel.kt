package com.example.mcoeexercise.screens.planetlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.mcoeexercise.network.Result
import com.example.mcoeexercise.utils.getIdFromUrl
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: PlanetRepository) : ViewModel() {
    private val _progressFlow = MutableStateFlow<Boolean>(false)
    val progressFlow: StateFlow<Boolean> = _progressFlow

    private val _planetList: MutableState<BaseResponse<Planet>?> = mutableStateOf(null)
    val planetList:MutableState<BaseResponse<Planet>?> = _planetList

    private val _planetListFlow = emptyFlow<PagingData<Planet>>()
    var planetListFlow: Flow<PagingData<Planet>> = _planetListFlow




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
                when(it){
                    is Result.Loading -> {
                        _progressFlow.value = true
                    }
                    is Result.Success<BaseResponse<Planet>> -> {
                        _progressFlow.value = false
                        _planetList.value = it.data
                    }

                    is Result.Error -> {
                        _progressFlow.value = false
                    }
                }
            }

        }
    }



}