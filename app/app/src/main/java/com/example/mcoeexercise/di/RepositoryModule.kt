package com.example.mcoeexercise.di

import com.example.mcoeexercise.network.ApiService
import com.example.mcoeexercise.repository.PlanetRepository
import com.example.mcoeexercise.repository.PlanetRepositoryImpl
import com.example.mcoeexercise.repository.PlanetsApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun providePlanetRepository(
        apiService: ApiService, planetsApiDataSource: PlanetsApiDataSource
    ): PlanetRepository {
        return PlanetRepositoryImpl(
            apiService, planetsApiDataSource
        )
    }



}