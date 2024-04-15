package com.example.mcoeexercise.repository


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlanetsApiDataSource @Inject constructor(private val apiService: ApiService) : PagingSource<Int, Planet>() {

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        return try {
            val nextPage = params.key ?: 1
            val planetList = apiService.getPlanetList(nextPage)
            LoadResult.Page(
                data = planetList.results,
                prevKey = nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
    }
}