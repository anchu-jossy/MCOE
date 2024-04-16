package com.example.mcoeexercise.screens.planetlist

// Import necessary modules and libraries

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mcoeexercise.R
import com.example.mcoeexercise.component.CircularIndeterminateProgressBar
import com.example.mcoeexercise.component.NoDataPlaceHolder
import com.example.mcoeexercise.component.NoInternetView
import com.example.mcoeexercise.component.TextLabelValue
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.navigation.Screen
import com.example.mcoeexercise.utils.ConnectionState
import com.example.mcoeexercise.utils.connectivityState
import com.example.mcoeexercise.utils.getIdFromUrl

/**
 * Composable function to display the list of planets.
 */
@Composable
fun PlanetList(navController: NavController) {
    val activity = (LocalContext.current as? Activity)
    val mainViewModel = hiltViewModel<MainViewModel>()
    val planetListFlow: LazyPagingItems<Planet> =
        mainViewModel.planetListFlow.collectAsLazyPagingItems()
    // Internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val progressBar =
        mainViewModel.progressFlow.collectAsState()

    val planetLists = mainViewModel.planetList.value

    if (isConnected.not()) {
        NoInternetView()
    } else {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.1f)
        LaunchedEffect(key1 = 0) {
            mainViewModel.getPlanets()
        }

        if (planetLists != null && !planetLists.results.isNullOrEmpty()) {
            Column(modifier = Modifier) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(planetLists.results) {
                        PlanetItemView(planet = it) {
                            val id = getIdFromUrl(it.url!!)
                            navController.navigate(Screen.PlanetDetail.route.plus("/${id}"))
                        }
                    }
                }
            }
        } else {
            NoDataPlaceHolder()
        }
    }
}

/**
 * Composable function to display each planet item.
 */
@Composable
fun PlanetItemView(planet: Planet, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = planet.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )
            TextLabelValue(stringResource(R.string.rotation_period), planet.rotationPeriod)
            TextLabelValue(stringResource(R.string.orbital_period), planet.orbitalPeriod)
            TextLabelValue(stringResource(R.string.diameter), planet.diameter)
            TextLabelValue(stringResource(R.string.climate), planet.climate)
            TextLabelValue(stringResource(R.string.gravity), planet.gravity)
            TextLabelValue(stringResource(R.string.terrain), planet.terrain)
            TextLabelValue(stringResource(R.string.population), planet.population)
        }
    }
}
