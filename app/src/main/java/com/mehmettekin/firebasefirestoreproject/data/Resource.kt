package com.mehmettekin.firebasefirestoreproject.data

sealed class Resource<out T>{
    data class Success<out T>(val result: T): Resource<T>()
    data class Failure(val exception: Exception):Resource<Nothing>()
    object Loading: Resource<Nothing>()
}