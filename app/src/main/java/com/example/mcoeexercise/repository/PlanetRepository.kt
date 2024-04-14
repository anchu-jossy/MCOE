package com.example.mcoeexercise.repository

import androidx.paging.PagingData
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.Result
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {

    suspend fun planetDetail(planetId: Int): Flow<Result<Planet>>

    suspend fun planetList(): Flow<PagingData<Planet>>
    suspend fun planets(): Flow<Result<BaseResponse<Planet>>>
    suspend fun film(filmId : Int) :  Flow<Result<Film>>
    suspend fun people(peopleId : Int) :  Flow<Result<People>>
}