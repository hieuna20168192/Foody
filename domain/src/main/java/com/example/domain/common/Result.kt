package com.example.domain.common

sealed class Result {
    data class Success<out T>(val data: T): Result()
    data class Failure(val t: Throwable): Result()
}
