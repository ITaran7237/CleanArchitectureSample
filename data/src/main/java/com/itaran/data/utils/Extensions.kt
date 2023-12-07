package com.itaran.data.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itaran.domain.models.CustomResult
import com.itaran.domain.models.error.ErrorLocalModel
import org.json.JSONObject
import retrofit2.Response

inline fun <reified T> Response<*>.parseErrJsonResponse(): T? {
    val gson = Gson()
    val type = object : TypeToken<T>() {}.type
    try {
        return gson.fromJson(errorBody()!!.charStream(), type)
    } catch (e: Exception) {
        Log.e("Extensions", e.message.toString())
    }
    return null
}

inline fun <reified T, reified R> CustomResult<T>.map(function: (T) -> R): CustomResult<R> =
    when (this) {
        is CustomResult.Success -> CustomResult.Success(function(this.data))
        is CustomResult.Failure -> CustomResult.Failure(this.error)
    }

fun <T> Response<T>.mapToResult(): CustomResult<T> {
    return if (this.isSuccessful) {
        CustomResult.Success(this.body()!!)
    } else {
        val jObjError = JSONObject(errorBody()?.string() ?: "")
        val error = ErrorLocalModel().convertToModel(jObjError)
        CustomResult.Failure(error)
    }
}

fun <T> Response<List<T>>.mapListToSingleResult(): CustomResult<T> {
    return if (this.isSuccessful) {
        CustomResult.Success(this.body()?.get(0)!!)
    } else {
        val jObjError = JSONObject(errorBody()?.string() ?: "")
        val error = ErrorLocalModel().convertToModel(jObjError)
        if (error.message.isNullOrEmpty()) {
            CustomResult.Failure(ErrorLocalModel(error.code, message = "Something went wrong"))
        } else {
            CustomResult.Failure(error)
        }
    }
}