package com.keyraco.widgetforge.widgets.complications

import androidx.compose.runtime.Composable
import androidx.glance.text.Text
import com.keyraco.widgetforge.data.model.WidgetComplication

@Composable
fun BatteryComplication(
    complication: WidgetComplication,
    cellWidth: Int = 1
) {
    Text("Battery")
}