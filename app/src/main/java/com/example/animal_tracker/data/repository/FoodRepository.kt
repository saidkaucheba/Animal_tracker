package com.example.animal_tracker.data.repository

import com.example.animal_tracker.data.SupabaseClient
import com.example.animal_tracker.data.model.Food
import io.github.jan.supabase.postgrest.from

class FoodRepository {
    suspend fun getAll(userId: Int): List<Food> {
        return SupabaseClient.client
            .from("Food")
            .select {
                filter { eq("User_id", userId) }
            }
            .decodeList<Food>()
    }

    suspend fun add(food: Food) {
        SupabaseClient.client
            .from("Food")
            .insert(food)
    }
}