package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityLog(
    val id: Int? = null,
    @SerialName("Pet_id") val petId: Int,
    @SerialName("Type") val type: String,
    @SerialName("Duration_min") val durationMin: Int? = null,
    @SerialName("Distance_km") val distanceKm: Double? = null,
    @SerialName("Repetitions") val repetitions: Int? = null,
    @SerialName("Started_at") val startedAt: String,
    @SerialName("Note") val note: String? = null
)