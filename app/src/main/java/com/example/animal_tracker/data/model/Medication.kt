package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Medication(
    val id: Int? = null,
    @SerialName("Pet_id") val petId: Int,
    @SerialName("Name") val name: String,
    @SerialName("Dosage") val dosage: String,
    @SerialName("Times_per_month") val timesPerMonth: String,
    @SerialName("Take_days") val takeDays: String? = null,
    @SerialName("Intake_time") val intakeTime: String,
    @SerialName("Start_date") val startDate: String,
    @SerialName("End_date") val endDate: String,
    @SerialName("Is_active") val isActive: Boolean = true
)