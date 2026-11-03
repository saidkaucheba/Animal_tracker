package com.example.animal_tracker.ui.weight

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.animal_tracker.R
import com.example.animal_tracker.data.image.rememberRemoteBitmap
import com.example.animal_tracker.data.model.Pet
import com.example.animal_tracker.data.model.WeightLog
import com.example.animal_tracker.ui.theme.Beige
import com.example.animal_tracker.ui.theme.CalendarDay
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@Composable
fun WeightScreen(
    pet: Pet,
    weightLogs: List<WeightLog>,
    onBack: () -> Unit,
    onAddWeight: (weightKg: Double, measuredAt: String, note: String?) -> Unit,
    onUpdateWeight: (logId: Int, weightKg: Double, measuredAt: String, note: String?) -> Unit
) {
    var period by remember { mutableStateOf("week") }
    var showPeriodMenu by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }

    var selectedLog by remember { mutableStateOf<WeightLog?>(null) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.nazad),
                    contentDescription = "Назад",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onBack() }
                )
                Spacer(Modifier.width(12.dp))

                val bitmap = rememberRemoteBitmap(pet.photoUrl)
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = pet.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(48.dp).clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.default_avatar),
                        contentDescription = pet.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(48.dp).clip(CircleShape)
                    )
                }

                Spacer(Modifier.width(12.dp))
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Вес питомца",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.surface
            )
        }

        Spacer(Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { showPeriodMenu = true }
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (period == "week") "Неделя" else "Месяц",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Image(
                        painter = painterResource(R.drawable.vniz),
                        contentDescription = "Выбрать период",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = showPeriodMenu,
                onDismissRequest = { showPeriodMenu = false },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(MaterialTheme.colorScheme.surface)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                DropdownMenuItem(
                    text = { Text("Неделя", color = MaterialTheme.colorScheme.onSurface) },
                    onClick = {
                        period = "week"
                        showPeriodMenu = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Месяц", color = MaterialTheme.colorScheme.onSurface) },
                    onClick = {
                        period = "month"
                        showPeriodMenu = false
                    }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        WeightCalendar(
            period = period,
            logs = weightLogs,
            onDayClick = { date, log ->
                selectedDate = date
                selectedLog = log
            }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { showAddDialog = true },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("+ Добавить замер", fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(24.dp))
    }

    if (selectedDate != null) {
        WeightDetailsDialog(
            date = selectedDate!!,
            log = selectedLog,
            onDismiss = {
                selectedDate = null
                selectedLog = null
            },
            onUpdate = { id, w, n ->
                val isoDate = "${selectedDate}T12:00:00Z"
                onUpdateWeight(id, w, isoDate, n)
                selectedDate = null
                selectedLog = null
            }
        )
    }

    if (showAddDialog) {
        AddWeightDialog(
            onDismiss = { showAddDialog = false },
            onSave = { weight, date, note ->
                onAddWeight(weight, date, note)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun WeightCalendar(
    period: String,
    logs: List<WeightLog>,
    onDayClick: (LocalDate, WeightLog?) -> Unit
) {
    val today = LocalDate.now()

    val byDate: Map<LocalDate, WeightLog> = logs
        .mapNotNull { log ->
            try {
                val d = LocalDate.parse(log.measureAt.substring(0, 10))
                d to log
            } catch (e: Exception) { null }
        }
        .groupBy { it.first }
        .mapValues { (_, values) -> values.maxByOrNull { it.second.weightKg }?.second!! }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        val monthText = if (period == "week") {
            val start = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            val end = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
            val fmt = DateTimeFormatter.ofPattern("d MMM")
            "${start.format(fmt)} - ${end.format(fmt)}"
        } else {
            YearMonth.from(today).atDay(1)
                .format(DateTimeFormatter.ofPattern("LLLL yyyy"))
                .replaceFirstChar { it.uppercase() }
        }

        Text(
            text = monthText,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(12.dp))

        Row(Modifier.fillMaxWidth()) {
            listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс").forEach { day ->
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        if (period == "week") {
            val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (i in 0..6) {
                    val date = startOfWeek.plusDays(i.toLong())
                    val log = byDate[date]
                    DayCell(
                        day = date.dayOfMonth,
                        weight = log?.weightKg,
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { onDayClick(date, log) }
                    )
                }
            }
        } else {
            val month = YearMonth.from(today)
            val firstOfMonth = month.atDay(1)
            val daysInMonth = month.lengthOfMonth()
            val startDayOfWeek = firstOfMonth.dayOfWeek.value - 1

            var weekIndex = 0
            while (true) {
                val firstDayOfRow = weekIndex * 7 - startDayOfWeek + 1
                if (firstDayOfRow > daysInMonth) break

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    for (i in 0..6) {
                        val cellDay = firstDayOfRow + i
                        if (cellDay in 1..daysInMonth) {
                            val date = month.atDay(cellDay)
                            val log = byDate[date]
                            DayCell(
                                day = cellDay,
                                weight = log?.weightKg,
                                modifier = Modifier.weight(1f),
                                onClick = { onDayClick(date, log) }
                            )
                        } else {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                }
                weekIndex++
            }
        }
    }
}

@Composable
private fun DayCell(
    day: Int,
    weight: Double?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(CalendarDay)
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = day.toString(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (weight != null) {
                Text(
                    text = "${"%.1f".format(weight)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun WeightDetailsDialog(
    date: LocalDate,
    log: WeightLog?,
    onDismiss: () -> Unit,
    onUpdate: (id: Int, weightKg: Double, note: String?) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editWeight by remember { mutableStateOf(log?.weightKg?.toString() ?: "") }
    var editNote by remember { mutableStateOf(log?.note ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Beige,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.height(16.dp))

                if (log == null) {
                    Text(
                        text = "Замеров за этот день нет",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface),
                        modifier = Modifier.fillMaxWidth().height(48.dp)
                    ) {
                        Text("Закрыть", fontWeight = FontWeight.Bold)
                    }
                } else {
                    if (isEditing) {
                        DialogField(editWeight, { editWeight = it }, "Вес (кг)")
                        Spacer(Modifier.height(10.dp))
                        DialogField(editNote, { editNote = it }, "Заметка")
                    } else {
                        Text(
                            text = "Вес: ${"%.1f".format(log.weightKg)} кг",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Заметка: ${log.note ?: "—"}",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    Row(Modifier.fillMaxWidth()) {
                        TextButton(
                            onClick = {
                                if (isEditing) {
                                    isEditing = false
                                    editWeight = log.weightKg.toString()
                                    editNote = log.note ?: ""
                                } else {
                                    onDismiss()
                                }
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface),
                            modifier = Modifier.weight(1f).height(48.dp)
                        ) {
                            Text("Отмена", fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (isEditing) {
                                    val w = editWeight.replace(',', '.').toDoubleOrNull()
                                    if (w != null) {
                                        onUpdate(log.id!!, w, editNote.trim().ifBlank { null })
                                    }
                                } else {
                                    isEditing = true
                                }
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface),
                            modifier = Modifier.weight(1f).height(48.dp)
                        ) {
                            Text(if (isEditing) "Сохранить" else "Изменить", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}