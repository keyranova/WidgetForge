package com.keyraco.widgetforge.builder.ui.complications

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.keyraco.widgetforge.data.model.WidgetComplication
import com.keyraco.widgetforge.ui.LocalTime
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DateComplication(
    complication: WidgetComplication,
    cellWidth: Int = 1
) {
    val currentTime = LocalTime.current
    val df = SimpleDateFormat("MMM d, yyy", Locale.getDefault())

    Text(
        text = df.format(currentTime),
        color = MaterialTheme.colorScheme.onSurface
    )
}