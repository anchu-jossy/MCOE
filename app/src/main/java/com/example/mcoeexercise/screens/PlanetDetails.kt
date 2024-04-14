package com.example.mcoeexercise.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mcoeexercise.MainViewModel
import com.example.mcoeexercise.R
import com.example.mcoeexercise.component.CircularIndeterminateProgressBar
import com.example.mcoeexercise.navigation.Screen
import com.example.mcoeexercise.utils.ConnectionState
import com.example.mcoeexercise.utils.connectivityState
import com.example.mcoeexercise.utils.getIdFromUrl

@Composable
fun PlanetDetails(navController: NavController, planetId: Int) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val artistDetail = mainViewModel.planetDetail
    val progressBar = remember { mutableStateOf(false) }

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    if (isConnected.not()) {
        Snackbar(
            action = {}, modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(R.string.no_internet))
        }
    } else {
        LaunchedEffect(true) {
            mainViewModel.planetDetails(planetId)
        }
        val planet = mainViewModel.planetDetail.value
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.1f)
        planet?.let {
            LaunchedEffect(key1 = true) { mainViewModel.planetFilms(it.films) }
        }

        planet?.let {
            Column {
                PlanetItemView(planet = planet) {}
                Text(
                    text = stringResource(id = R.string.films), modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )

                val filmList = mainViewModel.filmList.value
                filmList?.let {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(filmList) {
                            FilmItemView(it){}
                        }
                    }
                }
            }
        }
    }
}