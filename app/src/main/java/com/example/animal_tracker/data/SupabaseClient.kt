package com.example.animal_tracker.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {
    const val URL = "https://bijdigwyizhaiaznuyac.supabase.co"
    const val ANON_KEY = "sb_publishable_JW_TV5f1rWkyuReTnWh6-g_8ew5yYjn"

    val client = createSupabaseClient(
        supabaseUrl = URL,
        supabaseKey = ANON_KEY
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}