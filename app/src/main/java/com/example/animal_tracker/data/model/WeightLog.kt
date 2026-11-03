package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeightLog(
    val id: Int? = null,
    @SerialName("Pet_id")     val petId: Int,
    @SerialName("Weight_kg")  val weightKg: Double,
    @SerialName("Measure_at") val measureAt: String,
    @SerialName("Note")       val note: String? = null
)