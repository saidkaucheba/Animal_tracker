package com.example.animal_tracker.data.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

@Composable
fun rememberRemoteBitmap(url: String?): Bitmap? {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    LaunchedEffect(url) {
        bitmap = if (url.isNullOrBlank()) null
        else withContext(Dispatchers.IO) { loadAndCache(context, url) }
    }
    return bitmap
}

private suspend fun loadAndCache(context: Context, url: String): Bitmap? {
    return try {
        val fileName = url.hashCode().toString() + ".jpg"
        val file = File(context.cacheDir, fileName)
        if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            val bytes = URL(url).openStream().readBytes()
            file.writeBytes(bytes)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
    } catch (e: Exception) { null }
}