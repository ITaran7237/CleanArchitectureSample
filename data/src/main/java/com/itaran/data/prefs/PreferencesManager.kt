package com.itaran.data.prefs

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager : IPrefs {

    override fun setTokenTime(time: Long) {
        val editor = sharedPreferences!!.edit()
        editor.putLong(PROP_TOKEN_TIME, time)
        editor.apply()
    }

    override fun getTokenTime(): Long = sharedPreferences!!.getLong(PROP_TOKEN_TIME, 0L)

    override fun setIdToken(token: String) {
        val editor = sharedPreferences!!.edit()
        editor.putString(PROP_ID_TOKEN, token)
        editor.apply()
    }

    override fun getIdToken(): String = sharedPreferences!!.getString(PROP_ID_TOKEN, "")!!

    override fun setRefreshToken(token: String) {
        val editor = sharedPreferences!!.edit()
        editor.putString(PROP_REFRESH_TOKEN, token)
        editor.apply()
    }

    override fun getRefreshToken(): String = sharedPreferences!!.getString(PROP_REFRESH_TOKEN, "")!!

    override fun setAccessToken(token: String) {
        val editor = sharedPreferences!!.edit()
        editor.putString(PROP_TOKEN, token)
        editor.apply()
    }

    override fun getAccessToken(): String = sharedPreferences!!.getString(PROP_TOKEN, "")!!

    override fun setFirstLaunch(value: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(PROP_FIRST_INIT, value)
        editor.apply()
    }

    override fun isFirstLaunch(): Boolean = sharedPreferences!!.getBoolean(PROP_FIRST_INIT, true)

    override fun isUserLoggedIn(): Boolean {
        return getAccessToken().isNotEmpty()
    }

    override fun setUserFullName(name: String) {
        val editor = sharedPreferences!!.edit()
        editor.putString(PROP_USER_NAME, name)
        editor.apply()
    }

    override fun getUserFullName(): String = sharedPreferences!!.getString(PROP_USER_NAME, "")!!

    override fun setUserAvatar(avatar: String) {
        val editor = sharedPreferences!!.edit()
        editor.putString(PROP_USER_AVATAR, avatar)
        editor.apply()
    }

    override fun getUserAvatar(): String = sharedPreferences!!.getString(PROP_USER_AVATAR, "")!!

    override fun setNewNotificationCount(value: Int) {
        val editor = sharedPreferences!!.edit()
        editor.putInt(PROP_NOTIFICATION_COUNT, value)
        editor.apply()
    }

    override fun getNewNotificationCount(): Int =
        sharedPreferences!!.getInt(PROP_NOTIFICATION_COUNT, 0)

    override fun setUploadWifiOnly(value: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(PROP_UPLOAD_WIFI_ONLY, value)
        editor.apply()
    }

    override fun isUploadWifiOnly(): Boolean =
        sharedPreferences!!.getBoolean(PROP_UPLOAD_WIFI_ONLY, false)

    override fun setDeleteAfterUpload(value: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(PROP_DELETE_AFTER_UPLOAD, value)
        editor.apply()
    }

    override fun isWifiConnect(): Boolean =
        sharedPreferences!!.getBoolean(PROP_IS_WIFI_CONNECT, false)

    override fun setWifiConnect(value: Boolean) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(PROP_IS_WIFI_CONNECT, value)
        editor.apply()
    }

    override fun isDeleteAfterUpload(): Boolean =
        sharedPreferences!!.getBoolean(PROP_DELETE_AFTER_UPLOAD, false)

    override fun clearPrefs() {
        setFirstLaunch(true)
        setUploadWifiOnly(false)
        setWifiConnect(false)
        setAccessToken("")
        setIdToken("")
        setRefreshToken("")
        setUserFullName("")
        setTokenTime(0)
        setNewNotificationCount(0)
    }

    companion object {
        private const val PREFERENCES_NAME = "Shareds"
        private var sharedPreferences: SharedPreferences? = null

        const val PROP_FIRST_INIT: String = "PROP_FIRST_INIT"
        const val PROP_TOKEN: String = "PROP_TOKEN"
        const val PROP_ID_TOKEN: String = "PROP_ID_TOKEN"
        const val PROP_REFRESH_TOKEN: String = "PROP_REFRESH_TOKEN"
        const val PROP_TOKEN_TIME: String = "PROP_TOKEN_TIME"
        const val PROP_USER_NAME: String = "PROP_USER_NAME"
        const val PROP_USER_AVATAR: String = "PROP_USER_AVATAR"
        const val PROP_NOTIFICATION_COUNT: String = "PROP_NOTIFICATION_COUNT"
        const val PROP_UPLOAD_WIFI_ONLY: String = "PROP_UPLOAD_WIFI_ONLY"
        const val PROP_IS_WIFI_CONNECT: String = "PROP_IS_WIFI_CONNECT"
        const val PROP_DELETE_AFTER_UPLOAD: String = "PROP_DELETE_AFTER_UPLOAD"

        fun initializeShared(context: Context): PreferencesManager {
            sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            return PreferencesManager()
        }
    }
}