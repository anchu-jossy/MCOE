package com.example.mcoeexercise

import androidx.paging.PagingData
import com.example.mcoeexercise.di.RepositoryModule
import com.example.mcoeexercise.model.BaseResponse
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.Result
import com.example.mcoeexercise.repository.PlanetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
@Module
object FakeRepositoryModule {
    @Singleton
    @Provides
    fun provideFakeInventoryRepository() = object : PlanetRepository {
        /* @ExperimentalCoroutinesApi
         override suspend fun items(): Flow<List<Item>> {
             return flowOf(dummyItems)
         }*/

        override suspend fun planetDetail(planetId: Int): Flow<Result<Planet>> {
            return flowOf(Result.Success<Planet>(getPlanet()))
        }

        override suspend fun planetList(): Flow<PagingData<Planet>> {
            TODO("Not yet implemented")
        }

        override suspend fun planets(): Flow<Result<BaseResponse<Planet>>> {
            val response = BaseResponse<Planet>(
                2, getPlanetList(), "https://swapi.dev/api/planets/?page=2", null,
            )


            return flowOf(Result.Success<BaseResponse<Planet>>(response))
        }

        override suspend fun film(filmId: Int): Flow<Result<Film>> {
            TODO("Not yet implemented")
        }

        override suspend fun people(peopleId: Int): Flow<Result<People>> {
            TODO("Not yet implemented")
        }
    }

    fun getPlanet(): Planet {
        return Planet(
            "Tatooine",
            "23",
            "304",
            "10465",
            "arid",
            "1 standard",
            "desert",
            "1",
            "200000",
            arrayListOf(
                "https://swapi.dev/api/people/1/",
                "https://swapi.dev/api/people/2/",
                "https://swapi.dev/api/people/4/",
                "https://swapi.dev/api/people/6/",
                "https://swapi.dev/api/people/7/",
                "https://swapi.dev/api/people/8/",
                "https://swapi.dev/api/people/9/",
                "https://swapi.dev/api/people/11/",
                "https://swapi.dev/api/people/43/",
                "https://swapi.dev/api/people/62/"
            ),
            arrayListOf(
                "https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/4/",
                "https://swapi.dev/api/films/5/",
                "https://swapi.dev/api/films/6/"
            ),
            null,
            null,
            null
        )
    }

    fun getPlanetList(): ArrayList<Planet> {
        val arrayList = ArrayList<Planet>()
        arrayList.add(getPlanet())
        arrayList.add(getPlanet())
        arrayList.add(getPlanet())
        return arrayList

    }

}