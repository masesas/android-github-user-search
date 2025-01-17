package com.assesment.shared.utils

sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error(val exception: Exception) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data object Empty : Resource<Nothing>()
}