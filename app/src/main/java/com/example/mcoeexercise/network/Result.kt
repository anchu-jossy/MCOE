package com.example.mcoeexercise.network

/**
 * Sealed class representing different states of a network operation result.
 */
sealed class Result<out R> {
    /**
     * Represents a successful result containing data of type T.
     *
     * @param data The data returned upon success.
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents an error result containing an exception.
     *
     * @param exception The exception that occurred.
     */
    data class Error(val exception: Exception) : Result<Nothing>()

    /**
     * Represents a loading state, indicating that the operation is in progress.
     */
    data object Loading : Result<Nothing>()
}
