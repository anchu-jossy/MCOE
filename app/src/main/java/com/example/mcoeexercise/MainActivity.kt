package com.example.mcoeexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mcoeexercise.component.AppBarWithArrow
import com.example.mcoeexercise.model.Planet
import com.example.mcoeexercise.navigation.Navigation
import com.example.mcoeexercise.navigation.Screen
import com.example.mcoeexercise.navigation.currentRoute
import com.example.mcoeexercise.navigation.navigationTitle
import com.example.mcoeexercise.screens.PlanetList
import com.example.mcoeexercise.ui.theme.MCOEExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MCOEExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AllPlants()
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllPlants() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            when (currentRoute(navController)) {
                Screen.Planets.route -> {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = { Text(stringResource(R.string.planets_lbl)) }
                    )
                }
                else -> {
                    AppBarWithArrow(navController, navigationTitle(navController)) {
                        navController.popBackStack()
                    }
                }

            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxWidth().padding(innerPadding)
        ) {
            Navigation(navController, Modifier)
        }
           /* Navigation(navController, Modifier.padding(it), genreList.value)
            Column {
                CircularIndeterminateProgressBar(isDisplayed = searchProgressBar.value, 0.1f)
                if (isAppBarVisible.value.not()) {
                    SearchUI(navController, mainViewModel.searchData) {
                        isAppBarVisible.value = true
                    }
                }
            }
        }
        mainViewModel.searchData.pagingLoadingState {
            searchProgressBar.value = it
        }*/
    }


}






