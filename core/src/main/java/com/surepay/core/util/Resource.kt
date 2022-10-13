package com.surepay.core.util

sealed class Resource<out T> {
    data class Success<T>(val data: T?): Resource<T>()
    data class Error<T>(val error:Throwable, val message: T? ): Resource<T>()
}