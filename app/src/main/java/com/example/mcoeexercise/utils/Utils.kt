package com.example.mcoeexercise.utils

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import java.util.regex.Pattern


@Composable
fun <T : Any> LazyPagingItems<T>.pagingLoadingState(
    isLoaded: (pagingState: Boolean) -> Unit,
) {
    this.apply {
        when {
            // data is loading for first time
            loadState.refresh is LoadState.Loading -> {
                isLoaded(true)
            }
            // data is loading for second time or pagination
            loadState.append is LoadState.Loading -> {
                isLoaded(true)
            }
            loadState.refresh is LoadState.NotLoading -> {
                isLoaded(false)
            }
            loadState.append is LoadState.NotLoading -> {
                isLoaded(false)
            }
        }
    }
}

fun getIdFromUrl(url: String) : Int
{
    val uri = Uri.parse(url)
    return uri.lastPathSegment?.toInt() ?: 0
}
