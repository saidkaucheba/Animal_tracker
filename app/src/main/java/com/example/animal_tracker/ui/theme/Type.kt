package com.example.animal_tracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.animal_tracker.R

val Forum = FontFamily(
    Font(R.font.forum_regular, FontWeight.Normal)
)

val AppTypography = Typography(
    displayLarge  = TextStyle(fontFamily = Forum, fontSize = 44.sp, fontWeight = FontWeight.Normal),
    displayMedium = TextStyle(fontFamily = Forum, fontSize = 32.sp, fontWeight = FontWeight.Normal),
    titleLarge    = TextStyle(fontFamily = Forum, fontSize = 28.sp, fontWeight = FontWeight.Normal),
    titleMedium   = TextStyle(fontFamily = Forum, fontSize = 22.sp, fontWeight = FontWeight.Normal),
    bodyLarge     = TextStyle(fontFamily = Forum, fontSize = 20.sp),
    bodyMedium    = TextStyle(fontFamily = Forum, fontSize = 18.sp),
    bodySmall     = TextStyle(fontFamily = Forum, fontSize = 14.sp),
    labelLarge    = TextStyle(fontFamily = Forum, fontSize = 20.sp, fontWeight = FontWeight.Medium)
)