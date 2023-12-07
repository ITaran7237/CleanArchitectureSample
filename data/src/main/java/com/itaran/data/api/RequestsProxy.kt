package com.itaran.data.api

import com.itaran.data.repository.UpdateTokenRepository
import com.itaran.domain.models.CustomResult
import com.itaran.data.prefs.IPrefs
import retrofit2.Response

class RequestsProxy(private val prefs: IPrefs, private val updateToken: UpdateTokenRepository) {

    suspend fun <T> sendRequest(call: suspend () -> CustomResult<T>): CustomResult<T> {
        val result = call()
        when (result) {
            is CustomResult.Failure -> {
                if (isRefreshToken()) {
                    val updateTokenResultUpdateToken = updateToken.updateToken()
                    if (updateTokenResultUpdateToken is CustomResult.Success<*>) {
                        return call()
                    }
                }
            }
        }
        return result
    }

    suspend fun <T> sendRequestResponse(call: suspend () -> Response<T>): Response<T> {
        val result = call()
        if (!result.isSuccessful) {
            if (isRefreshToken()) {
                val updateTokenResultUpdateToken = updateToken.updateToken()
                if (updateTokenResultUpdateToken is CustomResult.Success<*>) {
                    return call()
                }
            }
        }
        return result
    }

    private fun isRefreshToken(): Boolean {
        val lastTokenTime =
            (((System.currentTimeMillis() / 1000) / 60) - ((prefs.getTokenTime() / 1000) / 60))
        return lastTokenTime >= TOKEN_MAX_TOKEN_TIME
    }

    companion object {
        private var TOKEN_MAX_TOKEN_TIME = 50
    }
}