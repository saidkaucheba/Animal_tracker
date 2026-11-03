package com.example.animal_tracker.data.repository

import com.example.animal_tracker.data.SupabaseClient
import com.example.animal_tracker.data.model.FeedingLog
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class FeedingRepository {
    suspend fun getByDate(petId: Int, startIso: String, endIso: String): List<FeedingLog> {
        return SupabaseClient.client
            .from("FeedingLog")
            .select {
                filter {
                    eq("Pet_id", petId)
                    gte("Fed_at", startIso)
                    lte("Fed_at", endIso)
                }
                order("Fed_at", Order.ASCENDING)
            }
            .decodeList<FeedingLog>()
    }

    suspend fun add(log: FeedingLog) {
        SupabaseClient.client.from("FeedingLog").insert(log)
    }

    suspend fun update(id: Int, amountG: Double, fedAt: String) {
        SupabaseClient.client
            .from("FeedingLog")
            .update({
                set("Amount_g", amountG)
                set("Fed_at", fedAt)
            }) {
                filter { eq("id", id) }
            }
    }

    suspend fun delete(id: Int) {
        SupabaseClient.client
            .from("FeedingLog")
            .delete {
                filter { eq("id", id) }
            }
    }
}