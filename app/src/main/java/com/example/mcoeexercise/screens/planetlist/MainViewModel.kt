package com.example.mcoeexercise.screens.planetlist

// Import necessary modules and libraries

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.Result
import com.example.mcoeexercise.repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Main screen, responsible for handling business logic related to the list of planets.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repo: PlanetRepository) : ViewModel() {
    private val _progressFlow = MutableStateFlow<Boolean>(false)
    val progressFlow: StateFlow<Boolean> = _progressFlow

    private val _planetList: MutableState<BaseResponse<Planet>?> = mutableStateOf(null)
    val planetList: MutableState<BaseResponse<Planet>?> = _planetList

    private val _planetListFlow = emptyFlow<PagingData<Planet>>()
    var planetListFlow: Flow<PagingData<Planet>> = _planetListFlow

    /**
     * Function to fetch the list of planets from the repository.
     */
    fun getPlanets() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.planets().collect {
                when (it) {
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
