package com.example.mcoeexercise.navigation

import androidx.annotation.StringRes
import com.example.mcoeexercise.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val objectName: String = "",
    val objectPath: String = ""
){
    object Planets : Screen("planets")
    object PlanetDetail : Screen("planetDetails", objectName = "planetId", objectPath = "/{planetId}" )


}