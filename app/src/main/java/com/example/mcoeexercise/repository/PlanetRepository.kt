package com.example.mcoeexercise.repository

// Import statement for PagingData
import androidx.paging.PagingData
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining methods to interact with planet-related data.
 */
interface PlanetRepository {

    /**
     * Fetches details of a specific planet.
     *
     * @param planetId The ID of the planet to fetch.
     * @return A Flow of Result containing the planet details.
     */
    suspend fun planetDetail(planetId: Int): Flow<Result<Planet>>

    /**
     * Fetches a flow of paginated planet data.
     *
     * @return A Flow of PagingData containing planets.
     */
    suspend fun planetList(): Flow<PagingData<Planet>>

    /**
     * Fetches a flow of planet data.
     *
     * @return A Flow of Result containing the base response of planets.
     */
    suspend fun planets(): Flow<Result<BaseResponse<Planet>>>

    /**
     * Fetches details of a specific film.
     *
     * @param filmId The ID of the film to fetch.
     * @return A Flow of Result containing the film details.
     */
    suspend fun film(filmId: Int): Flow<Result<Film>>

    /**
     * Fetches details of a specific character (person).
     *
     * @param peopleId The ID of the character to fetch.
     * @return A Flow of Result containing the character details.
     */
    suspend fun people(peopleId: Int): Flow<Result<People>>
}
