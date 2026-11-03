package com.example.animal_tracker.navigation

object Routes {
    const val MAIN      = "main"
    const val WEIGHT    = "weight/{petId}"
    const val ACTIVITY  = "activity/{petId}"
    const val VACCINES  = "vaccines/{petId}"
    const val FEEDING   = "feeding/{petId}"
    const val MEDS      = "meds/{petId}"
    const val PROFILE   = "profile"

    fun weight(petId: Int)   = "weight/$petId"
    fun activity(petId: Int) = "activity/$petId"
    fun vaccines(petId: Int) = "vaccines/$petId"
    fun feeding(petId: Int)  = "feeding/$petId"
    fun meds(petId: Int)     = "meds/$petId"
}