package com.example.dailyforecastapp.base

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T? = null) : ViewState<T>()
    data class Error(val exeption: Exception) : ViewState<Nothing>()
}