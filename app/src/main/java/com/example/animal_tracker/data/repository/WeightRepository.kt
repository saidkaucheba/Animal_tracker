package com.example.animal_tracker.data.repository

import com.example.animal_tracker.data.SupabaseClient
import com.example.animal_tracker.data.model.WeightLog
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class WeightRepository {

    suspend fun getByPet(petId: Int): List<WeightLog> {
        return SupabaseClient.client
            .from("WeightLog")
            .select {
                filter { eq("Pet_id", petId) }
                order("Measure_at", Order.DESCENDING)
            }
            .decodeList<WeightLog>()
    }

    suspend fun add(log: WeightLog) {
        SupabaseClient.client
            .from("WeightLog")
            .insert(log)
    }

    suspend fun update(id: Int, weightKg: Double, note: String?) {
        SupabaseClient.client
            .from("WeightLog")
            .update({
                set("Weight_kg", weightKg)
                set("Note", note)
            }) {
                filter { eq("id", id) }
            }
    }
}