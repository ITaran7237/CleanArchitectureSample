package com.itaran.data.api.models.response.refreshtoken

data class RefreshTokenModelResponse(
    var idToken: String,
    var refreshToken: String,
    var accessToken: String
)