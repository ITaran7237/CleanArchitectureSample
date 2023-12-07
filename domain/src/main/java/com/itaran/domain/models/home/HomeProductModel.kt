package com.itaran.domain.models.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class HomeProductModel(
    val sessionId: String,
    val total: Int,
    val limit: Int,
    val skip: Int,
    var date: Date,
    var sourceUrl: String? = null,
    val targetId: String,
    var company: String,
    var email: String,
    var firstName: String,
    var lastName: String,
    var isUploading: Boolean = false,
    var progress: Double = 100.0,
    val emailSend: Boolean,
    val type: String?,
    val userAvatar: String?,
    val userName: String?
): Parcelable