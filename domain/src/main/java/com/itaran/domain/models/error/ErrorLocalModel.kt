package com.itaran.domain.models.error

import org.json.JSONObject

data class ErrorLocalModel(
    val code: Int? = null,
    val message: String? = null
) {
    fun convertToModel(errorJson: JSONObject): ErrorLocalModel {
        return ErrorLocalModel(
            errorJson.getInt("code"),
            errorJson.getString("message")
        )
    }
}