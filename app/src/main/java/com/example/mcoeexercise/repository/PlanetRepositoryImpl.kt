package com.example.mcoeexercise.repository

// Import statements for necessary classes and interfaces
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

/**
 * Implementation of [PlanetRepository] that interacts with the API and data sources.
 *
 * @param apiService The ApiService used to fetch data from the API.
 * @param planetsApiDataSource The data source for paginated planet data.
 */
class PlanetRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val planetsApiDataSource: PlanetsApiDataSource
) : PlanetRepository {

    /**
     * Fetches details of a specific planet.
     *
     * @param planetId The ID of the planet to fetch.
     * @return A Flow of Result containing the planet details.
     */
    override suspend fun planetDetail(planetId: Int): Flow<Result<Planet>> = flow {
        emit(Result.Loading)
        try {
            val searchResult = apiService.getPlanetDetail(planetId)
            emit(Result.Success(searchResult))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Fetches a flow of paginated planet data.
     *
     * @return A Flow of PagingData containing planets.
     */
    override suspend fun planetList(): Flow<PagingData<Planet>> = Pager(
        pagingSourceFactory = { planetsApiDataSource },
        config = PagingConfig(pageSize = 10),
        initialKey = 1,
    ).flow

    /**
     * Fetches a flow of planet data.
     *
     * @return A Flow of Result containing the base response of planets.
     */
    override suspend fun planets(): Flow<Result<BaseResponse<Planet>>> = flow {
        emit(Result.Loading)
        try {
            val searchResult = apiService.getPlanetList(1)
            emit(Result.Success(searchResult))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Fetches details of a specific film.
     *
     * @param filmId The ID of the film to fetch.
     * @return A Flow of Result containing the film details.
     */
    override suspend fun film(filmId: Int): Flow<Result<Film>> = flow {
        emit(Result.Loading)
        try {
            val searchResult = apiService.getFilmDetail(filmId)
            emit(Result.Success(searchResult))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Fetches details of a specific character (person).
     *
     * @param peopleId The ID of the character to fetch.
     * @return A Flow of Result containing the character details.
     */
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
