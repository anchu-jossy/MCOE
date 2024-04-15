package com.example.mcoeexercise.network

import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.model.Planet
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

//https://swapi.dev/
interface ApiService {


    @GET("/api/planets/")
    suspend fun getPlanetList(@Query("page") page: Int): BaseResponse<Planet>

    @GET("/api/planets/{id}")
    suspend fun getPlanetDetail(@Path("id") id: Int): Planet

    @GET("/api/films/{id}")
    suspend fun getFilmDetail(@Path("id") id: Int): Film

    @GET("/api/people/{id}")
    suspend fun getPeopleDetail(@Path("id") id: Int): People

}