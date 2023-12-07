package com.itaran.data.api

import com.itaran.data.api.models.response.refreshtoken.RefreshTokenModelResponse
import retrofit2.Response
import retrofit2.http.GET

interface RefreshTokenApi {

    @GET("refreshtoken")
    suspend fun refreshToken(): Response<List<RefreshTokenModelResponse>>
}