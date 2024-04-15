package com.example.mcoeexercise.model

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("title") var title: String,
    @SerializedName("episode_id") var episodeId: Int? = null,
    @SerializedName("opening_crawl") var openingCrawl: String? = null,
    @SerializedName("director") var director: String? = null,
    @SerializedName("producer") var producer: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null
)
