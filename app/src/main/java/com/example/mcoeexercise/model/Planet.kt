package com.example.mcoeexercise.model

// Import statement for Gson annotation
import com.google.gson.annotations.SerializedName

/**
 * Data class representing a planet with various attributes.
 *
 * @param name The name of the planet.
 * @param rotationPeriod The rotation period of the planet.
 * @param orbitalPeriod The orbital period of the planet.
 * @param diameter The diameter of the planet.
 * @param climate The climate of the planet.
 * @param gravity The gravity of the planet.
 * @param terrain The terrain of the planet.
 * @param surfaceWater The percentage of surface water on the planet.
 * @param population The population of the planet.
 * @param residents The list of URLs of residents of the planet.
 * @param films The list of URLs of films featuring the planet.
 * @param created The date when the planet was created.
 * @param edited The date when the planet was last edited.
 * @param url The URL of the planet.
 */
data class Planet(
    @SerializedName("name")
    var name: String ,
    @SerializedName("rotation_period")
    var rotationPeriod: String? = null,
    @SerializedName("orbital_period")
    var orbitalPeriod: String? = null,
    @SerializedName("diameter")
    var diameter: String? = null,
    @SerializedName("climate")
    var climate: String? = null,
    @SerializedName("gravity")
    var gravity: String? = null,
    @SerializedName("terrain")
    var terrain: String? = null,
    @SerializedName("surface_water")
    var surfaceWater: String? = null,
    @SerializedName("population")
    var population: String? = null,
    @SerializedName("residents")
    var residents: ArrayList<String> = arrayListOf(),
    @SerializedName("films")
    var films: ArrayList<String> = arrayListOf(),
    @SerializedName("created")
    var created: String? = null,
    @SerializedName("edited")
    var edited: String? = null,
    @SerializedName("url")
    var url: String? = null
) {
}
