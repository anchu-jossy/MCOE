package com.example.mcoeexercise.model

// Import statement for Gson annotation
import com.google.gson.annotations.SerializedName

/**
 * Data class representing a person with various attributes.
 *
 * @param name The name of the person.
 * @param height The height of the person.
 * @param mass The mass of the person.
 * @param hairColor The hair color of the person.
 * @param skinColor The skin color of the person.
 * @param eyeColor The eye color of the person.
 * @param birthYear The birth year of the person.
 * @param gender The gender of the person.
 */
data class People(
    @SerializedName("name") var name: String? = null,
    @SerializedName("height") var height: String? = null,
    @SerializedName("mass") var mass: String? = null,
    @SerializedName("hair_color") var hairColor: String? = null,
    @SerializedName("skin_color") var skinColor: String? = null,
    @SerializedName("eye_color") var eyeColor: String? = null,
    @SerializedName("birth_year") var birthYear: String? = null,
    @SerializedName("gender") var gender: String? = null
)
