package com.itaran.data.prefs

interface IPrefs {

    fun setFirstLaunch(value: Boolean)
    fun isFirstLaunch(): Boolean

    fun setAccessToken(token: String)
    fun getAccessToken(): String

    fun setIdToken(token: String)
    fun getIdToken(): String

    fun setRefreshToken(token: String)
    fun getRefreshToken(): String

    fun setTokenTime(time: Long)
    fun getTokenTime(): Long

    fun isUserLoggedIn(): Boolean

    fun setUserFullName(name: String)
    fun getUserFullName(): String

    fun setUserAvatar(avatar: String)
    fun getUserAvatar(): String

    fun setNewNotificationCount(value: Int)
    fun getNewNotificationCount(): Int

    fun setUploadWifiOnly(value: Boolean)
    fun isUploadWifiOnly(): Boolean

    fun isWifiConnect(): Boolean
    fun setWifiConnect(value: Boolean)

    fun setDeleteAfterUpload(value: Boolean)
    fun isDeleteAfterUpload(): Boolean

    fun clearPrefs()
}