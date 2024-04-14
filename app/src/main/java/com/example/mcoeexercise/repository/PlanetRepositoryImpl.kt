package com.example.mcoeexercise.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.ApiService
import com.example.mcoeexercise.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(private val apiService: ApiService,  private val planetsApiDataSource: PlanetsApiDataSource) : PlanetRepository{

/*    override fun planetList(): Flow<PagingData<Planet>> = Pager(
        pagingSourceFactory = { PlanetsApiDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow*/

    override suspend fun planetDetail(planetId: Int): Flow<Result<Planet>>  = flow{
        emit(Result.Loading)
        try {
            val searchResult = apiService.getPlanetDetail(planetId)
            emit(Result.Success(searchResult))

        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun planetList(): Flow<PagingData<Planet>> = Pager(
        pagingSourceFactory = { planetsApiDataSource},
        config = PagingConfig(pageSize = 10),
        initialKey = 1,
    ).flow

    override suspend fun planets(): Flow<Result<BaseResponse<Planet>>> = flow{
        emit(Result.Loading)
        try {
            val searchResult = apiService.getPlanetList(1)
            emit(Result.Success(searchResult))

        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun film(filmId: Int): Flow<Result<Film>> = flow {
        emit(Result.Loading)
        try {
            val searchResult = apiService.getFilmDetail(filmId)
            emit(Result.Success(searchResult))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun people(peopleId: Int): Flow<Result<People>> = flow {
        emit(Result.Loading)
        try {
            val searchResult = apiService.getPeopleDetail(peopleId)
            emit(Result.Success(searchResult))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}