package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: Int? = null,
    @SerialName("Email")      val email: String,
    @SerialName("Name")       val name: String? = null,
    @SerialName("Avatar_url") val avatarUrl: String? = null,
    @SerialName("auth_id")    val authId: String
)