package com.itaran.domain.models

import com.itaran.domain.models.error.ErrorLocalModel

sealed class CustomResult<T> {
    data class Success<T>(val data: T) : CustomResult<T>()
    data class Failure<T>(val error: ErrorLocalModel) : CustomResult<T>()
}