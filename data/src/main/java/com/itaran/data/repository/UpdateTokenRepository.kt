package com.itaran.data.repository

import com.itaran.data.api.RefreshTokenApi
import com.itaran.data.utils.mapListToSingleResult
import com.itaran.domain.models.CustomResult
import com.itaran.data.api.models.response.refreshtoken.RefreshTokenModelResponse
import com.itaran.data.prefs.IPrefs

class UpdateTokenRepository(private val api: RefreshTokenApi, private val prefs: IPrefs) {

    suspend fun updateToken(): CustomResult<RefreshTokenModelResponse> {
        val result = api.refreshToken().mapListToSingleResult()
        when (result) {
            is CustomResult.Success -> {
                val response = result.data
                prefs.setIdToken(response.idToken)
                prefs.setRefreshToken(response.refreshToken)
                prefs.setAccessToken(response.accessToken)
                prefs.setTokenTime(System.currentTimeMillis())
            }
        }
        return result
    }
}