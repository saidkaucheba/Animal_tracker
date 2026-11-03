package com.example.animal_tracker.data.repository

import com.example.animal_tracker.data.SupabaseClient
import com.example.animal_tracker.data.model.Reminder
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class ReminderRepository {
    suspend fun getByPet(petId: Int): List<Reminder> {
        return SupabaseClient.client.from("Reminder")
            .select {
                filter { eq("Pet_id", petId) }
                order("Remind_at", Order.ASCENDING)
            }.decodeList()
    }

    suspend fun add(reminder: Reminder) {
        SupabaseClient.client.from("Reminder").insert(reminder)
    }

    suspend fun toggleDone(id: Int, isDone: Boolean) {
        SupabaseClient.client.from("Reminder").update({ set("Is_done", isDone) }) {
            filter { eq("id", id) }
        }
    }

    suspend fun delete(id: Int) {
        SupabaseClient.client.from("Reminder").delete { filter { eq("id", id) } }
    }
}