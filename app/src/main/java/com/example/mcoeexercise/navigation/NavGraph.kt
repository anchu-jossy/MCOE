package com.example.mcoeexercise.navigation

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

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier)
{

    val navGraph = remember(navController) {
        navController.createGraph(startDestination = Screen.Planets.route) {
            composable(Screen.Planets.route) {
                PlanetList(
                    navController = navController,

                    )
            }
            composable(Screen.PlanetDetail.route.plus(Screen.PlanetDetail.objectPath), arguments =  listOf(navArgument(Screen.PlanetDetail.objectName) {
                type = NavType.IntType
            }))
            {
                val planetId = it.arguments?.getInt(Screen.PlanetDetail.objectName)
                planetId?.let {
                    PlanetDetails(navController, planetId)
                }
            }
        }
    }
    NavHost(navController, navGraph)
    /*NavHost(navController = navController, startDestination = Screen.Planets.route, modifier =  modifier){

        composable(Screen.Planets.route) {
            PlanetList(
                navController = navController,

            )
        }
        composable(Screen.PlanetDetail.route, arguments =  listOf(navArgument(Screen.PlanetDetail.objectName) {
            type = NavType.IntType
        }))
        {
            val planetId = it.arguments?.getInt(Screen.PlanetDetail.objectName)
            planetId?.let {
                PlanetDetails(navController, planetId)
            }
        }
    }*/
}

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

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}