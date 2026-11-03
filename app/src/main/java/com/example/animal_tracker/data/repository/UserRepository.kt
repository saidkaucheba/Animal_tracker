package com.example.animal_tracker.data.repository

import com.example.animal_tracker.data.SupabaseClient
import com.example.animal_tracker.data.model.UserProfile
import io.github.jan.supabase.postgrest.from

class UserRepository {

    suspend fun getByAuthId(authId: String): UserProfile? {
        return SupabaseClient.client
            .from("User")
            .select {
                filter { eq("auth_id", authId) }
                limit(1)
            }
            .decodeSingleOrNull<UserProfile>()
    }
}