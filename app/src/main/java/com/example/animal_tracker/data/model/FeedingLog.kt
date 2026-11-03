package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedingLog(
    val id: Int? = null,
    @SerialName("Pet_id") val petId: Int,
    @SerialName("Food_id") val foodId: Int,
    @SerialName("Amount_g") val amountG: Double,
    @SerialName("Fed_at") val fedAt: String,
    @SerialName("Note") val note: String? = null
)