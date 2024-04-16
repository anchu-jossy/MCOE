package com.example.mcoeexercise.network

// Import statement for Retrofit annotations
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.model.Planet
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Retrofit interface defining API endpoints.
 */
interface ApiService {

    /**
     * Fetches a paginated list of planets from the API.
     *
     * @param page The page number of the results to fetch.
     * @return BaseResponse containing a list of planets.
     */
    @GET("/api/planets/")
    suspend fun getPlanetList(@Query("page") page: Int): BaseResponse<Planet>

    /**
     * Fetches details of a specific planet from the API.
     *
     * @param id The ID of the planet to fetch.
     * @return The planet details.
     */
    @GET("/api/planets/{id}")
    suspend fun getPlanetDetail(@Path("id") id: Int): Planet

    /**
     * Fetches details of a specific film from the API.
     *
     * @param id The ID of the film to fetch.
     * @return The film details.
     */
    @GET("/api/films/{id}")
    suspend fun getFilmDetail(@Path("id") id: Int): Film

    /**
     * Fetches details of a specific character (person) from the API.
     *
     * @param id The ID of the character to fetch.
     * @return The character details.
     */
    @GET("/api/people/{id}")
    suspend fun getPeopleDetail(@Path("id") id: Int): People

}
