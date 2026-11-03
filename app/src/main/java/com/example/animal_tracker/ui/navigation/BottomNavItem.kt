package com.example.animal_tracker.ui.navigation

import androidx.annotation.DrawableRes
import com.example.animal_tracker.R

sealed class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Reminders : BottomNavItem("reminders", "Напоминания", R.drawable.napomin)
    object Pets      : BottomNavItem("pets",      "Мои питомцы", R.drawable.pitom)
    object Home      : BottomNavItem("home",      "Главная",     R.drawable.glav)
    object Profile   : BottomNavItem("profile",   "Профиль",     R.drawable.profil)
}