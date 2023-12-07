package com.itaran.data.api

import com.itaran.domain.models.home.HomeProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("sessions")
    suspend fun getMyItems(@Query("\$sort[createdAt]") sort: Int): Response<HomeProductModel>
}