package com.example.animal_tracker.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pet(
    val id: Int? = null,
    @SerialName("User_id")    val userId: Int,
    @SerialName("Name")       val name: String,
    @SerialName("Species")    val species: String? = null,
    @SerialName("Breed")      val breed: String? = null,
    @SerialName("Birth_day")  val birthDay: String? = null,
    @SerialName("Gender")     val gender: String? = null,
    @SerialName("Photo_url")  val photoUrl: String? = null
)