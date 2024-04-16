package com.example.mcoeexercise.model

// Import statement for Gson annotation
import com.google.gson.annotations.SerializedName

/**
 * Data class representing a film with various attributes.
 *
 * @param title The title of the film.
 * @param episodeId The episode number of the film.
 * @param openingCrawl The opening crawl of the film.
 * @param director The director(s) of the film.
 * @param producer The producer(s) of the film.
 * @param releaseDate The release date of the film.
 */
data class Film(
    @SerializedName("title") var title: String,
    @SerializedName("episode_id") var episodeId: Int? = null,
    @SerializedName("opening_crawl") var openingCrawl: String? = null,
    @SerializedName("director") var director: String? = null,
    @SerializedName("producer") var producer: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null
)
