package com.example.domain.common

sealed class Result<out T> {
    open class Success<out T>(val data: T): Result<T>()
    data class Failure(val t: Throwable): Result<Nothing>()
}
