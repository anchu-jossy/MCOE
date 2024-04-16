package com.example.mcoeexercise.model

// Import statement for Gson annotation
import com.google.gson.annotations.SerializedName

/**
 * Generic data class representing a base response containing count, results, next, and previous fields.
 *
 * @param count The count of results.
 * @param results The list of results of type T.
 * @param next The URL for the next page, if available.
 * @param previous The URL for the previous page, if available.
 */
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
