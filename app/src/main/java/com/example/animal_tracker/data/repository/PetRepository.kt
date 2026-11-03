package com.example.animal_tracker.data.repository

import com.example.animal_tracker.data.SupabaseClient
import com.example.animal_tracker.data.model.Pet
import com.example.animal_tracker.data.model.WeightLog
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class PetRepository {

    suspend fun getPetsByUser(userId: Int): List<Pet> {
        return SupabaseClient.client
            .from("Pet")
            .select { filter { eq("User_id", userId) } }
            .decodeList<Pet>()
    }

    suspend fun getLastWeight(petId: Int): WeightLog? {
        return SupabaseClient.client
            .from("WeightLog")
            .select {
                filter { eq("Pet_id", petId) }
                order("Measure_at", Order.DESCENDING)
                limit(1)
            }
            .decodeSingleOrNull<WeightLog>()
    }

    suspend fun addPet(pet: Pet) {
        SupabaseClient.client
            .from("Pet")
            .insert(pet)
    }

    suspend fun addPetAndReturn(pet: Pet): Pet {
        return SupabaseClient.client
            .from("Pet")
            .insert(pet) {
                select()
            }
            .decodeSingle<Pet>()
    }

    suspend fun updatePhoto(petId: Int, photoUrl: String) {
        SupabaseClient.client
            .from("Pet")
            .update({
                set("Photo_url", photoUrl)
            }) {
                filter { eq("id", petId) }
            }
    }

    suspend fun deletePet(petId: Int) {
        SupabaseClient.client
            .from("Pet")
            .delete {
                filter { eq("id", petId) }
            }
    }
}