package com.example.animal_tracker.data.repository

import com.example.animal_tracker.data.SupabaseClient
import com.example.animal_tracker.data.model.ActivityLog
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class ActivityRepository {
    suspend fun getByPet(petId: Int): List<ActivityLog> {
        return SupabaseClient.client
            .from("ActivityLog")
            .select {
                filter { eq("Pet_id", petId) }
                order("Started_at", Order.DESCENDING)
            }
            .decodeList<ActivityLog>()
    }

    suspend fun add(log: ActivityLog) {
        SupabaseClient.client.from("ActivityLog").insert(log)
    }
}