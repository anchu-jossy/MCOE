package com.example.mcoeexercise.screens.planet_details

// Import statements for necessary classes and interfaces
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mcoeexercise.screens.planetlist.MainViewModel
import com.example.mcoeexercise.R
import com.example.mcoeexercise.component.CircularIndeterminateProgressBar
import com.example.mcoeexercise.component.NoInternetView
import com.example.mcoeexercise.screens.planetlist.PlanetItemView
import com.example.mcoeexercise.utils.ConnectionState
import com.example.mcoeexercise.utils.connectivityState

/**
 * Composable function to display details of a planet.
 *
 * @param navController The NavController for navigation.
 * @param planetId The ID of the planet whose details are being displayed.
 */
@Composable
fun PlanetDetails(navController: NavController, planetId: Int) {
    val planetDetailViewModel = hiltViewModel<PlanetDetailViewModel>()
    val progressBar = planetDetailViewModel.progressFlow.collectAsState()

    // Check internet connectivity
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // Show no internet view if not connected
    if (isConnected.not()) {
        NoInternetView()
    } else {
        // Fetch planet details when connected
        LaunchedEffect(true) {
            planetDetailViewModel.planetDetails(planetId)
        }

        val planet = planetDetailViewModel.planetDetail.value
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.1f)

        // Fetch films and residents associated with the planet
        planet?.let {
            LaunchedEffect(key1 = true) { planetDetailViewModel.planetFilms(it.films) }
            LaunchedEffect(key1 = true) { planetDetailViewModel.planetPeople(it.residents) }
        }

        // Display planet details if available
        planet?.let {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                PlanetItemView(planet = planet) {}
                Spacer(modifier = Modifier.fillMaxWidth().padding(8.dp))
                // Section: Films
                Text(
                    text = stringResource(id = R.string.films),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                )
                val filmList = planetDetailViewModel.filmList.value
                filmList?.let {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(filmList) {
                            FilmItemView(it){}
                        }
                    }
                }

                Spacer(modifier = Modifier.fillMaxWidth().padding(8.dp))
                // Section: Residents
                Text(
                    text = stringResource(id = R.string.residents),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                )
                val peopleList = planetDetailViewModel.peopleList.value
                peopleList?.let {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(peopleList) {
                            ResidentItem(it){}
                        }
                    }
                }
            }
        }
    }
}
