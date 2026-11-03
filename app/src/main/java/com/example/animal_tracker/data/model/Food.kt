package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val id: Int? = null,
    @SerialName("User_id") val userId: Int,
    @SerialName("Brand") val brand: String,
    @SerialName("Name") val name: String,
    @SerialName("Calories_per_100g") val caloriesPer100g: Double
)