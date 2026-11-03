package com.example.animal_tracker.ui.main.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color          // ← НОВЫЙ импорт
import androidx.compose.ui.unit.dp

@Composable
fun SectionDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        thickness = 2.dp,                          // ← ИЗМЕНЕНО: было 1.dp — теперь жирнее
        color = Color.Black                        // ← ИЗМЕНЕНО: было DarkBrown 30% — теперь чёрный
    )
}