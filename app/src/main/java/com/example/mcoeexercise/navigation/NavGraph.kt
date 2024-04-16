package com.example.mcoeexercise.navigation

// Import statements for necessary Compose components and navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.mcoeexercise.R
import com.example.mcoeexercise.screens.planet_details.PlanetDetails
import com.example.mcoeexercise.screens.planetlist.PlanetList

/**
 * Composable function for setting up navigation within the app.
 *
 * @param navController The NavHostController for navigation.
 * @param modifier Modifier for the navigation composable.
 */
@Composable
fun Navigation(navController: NavHostController, modifier: Modifier) {

    val navGraph = remember(navController) {
        navController.createGraph(startDestination = Screen.Planets.route) {
            // Defining composable destinations within the navigation graph
            composable(Screen.Planets.route) {
                // Displaying the planet list screen
                PlanetList(navController = navController)
            }
            composable(Screen.PlanetDetail.route.plus(Screen.PlanetDetail.objectPath), arguments = listOf(navArgument(Screen.PlanetDetail.objectName) {
                type = NavType.IntType
            })) { backStackEntry ->
                // Extracting planet ID from arguments
                val planetId = backStackEntry.arguments?.getInt(Screen.PlanetDetail.objectName)
                planetId?.let {
                    // Displaying planet details screen
                    PlanetDetails(navController, planetId)
                }
            }
        }
    }
    // Creating NavHost with the provided NavHostController and navigation graph
    NavHost(navController, navGraph)
}

/**
 * Composable function to get the title based on the current navigation route.
 *
 * @param navController The NavController for navigation.
 * @return The title corresponding to the current route.
 */
@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.Planets.route -> stringResource(id = R.string.planets_lbl)
        Screen.PlanetDetail.route -> stringResource(id = R.string.planet_details)
        else -> {
            ""
        }
    }
}

/**
 * Function to get the current route from the NavController.
 *
 * @param navController The NavController for navigation.
 * @return The current route as a string.
 */
@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
