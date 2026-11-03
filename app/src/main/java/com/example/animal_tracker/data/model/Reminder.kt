package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reminder(
    val id: Int? = null,
    @SerialName("Pet_id") val petId: Int,
    @SerialName("Type") val type: String,
    @SerialName("Title") val title: String,
    @SerialName("Remind_at") val remindAt: String,
    @SerialName("Is_done") val isDone: Boolean = false
)