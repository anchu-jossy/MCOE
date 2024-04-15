package com.example.mcoeexercise.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("name") var name: String,
    @SerializedName("height") var height: String? = null,
    @SerializedName("mass") var mass: String? = null,
    @SerializedName("hair_color") var hairColor: String? = null,
    @SerializedName("skin_color") var skinColor: String? = null,
    @SerializedName("eye_color") var eyeColor: String? = null,
    @SerializedName("birth_year") var birthYear: String? = null,
    @SerializedName("gender") var gender: String? = null
)
