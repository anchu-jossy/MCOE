package com.example.mcoeexercise.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
)