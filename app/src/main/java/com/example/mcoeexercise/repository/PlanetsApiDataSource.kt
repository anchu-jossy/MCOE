package com.example.mcoeexercise.repository

// Import statements for necessary classes and interfaces
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * PagingSource implementation for fetching paginated planet data from the API.
 *
 * @param apiService The ApiService used to fetch data from the API.
 */
class PlanetsApiDataSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, Planet>() {

    /**
     * Returns the key for the page to be loaded next based on the current state.
     *
     * @param state The current PagingState.
     * @return The key for the next page to be loaded.
     */
    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    /**
     * Loads the requested page from the API.
     *
     * @param params The LoadParams specifying the page to load.
     * @return The LoadResult containing the loaded data.
     */
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
