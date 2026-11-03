package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vaccination(
    val id: Int? = null,
    @SerialName("Pet_id") val petId: Int,
    @SerialName("Vaccine_name") val vaccineName: String,
    @SerialName("Date_given") val dateGiven: String,
    @SerialName("Next_due_date") val nextDueDate: String? = null,
    @SerialName("Vet_name") val vetName: String? = null,
    @SerialName("Clinic") val clinic: String? = null,
    @SerialName("Note") val note: String? = null
)