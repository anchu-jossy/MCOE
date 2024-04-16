package com.example.mcoeexercise.navigation

// Import statement for String resource annotation
import androidx.annotation.StringRes
import com.example.mcoeexercise.R

/**
 * Sealed class representing different screens in the app.
 *
 * @param route The route identifier for the screen.
 * @param title The string resource ID for the title of the screen.
 * @param objectName The name of the object used as argument for the screen.
 * @param objectPath The path of the object used as argument for the screen.
 */
sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val objectName: String = "",
    val objectPath: String = ""
){
    // Screen object for displaying the list of planets
    object Planets : Screen("planets")

    // Screen object for displaying details of a planet
    object PlanetDetail : Screen("planetDetails", objectName = "planetId", objectPath = "/{planetId}" )
}
