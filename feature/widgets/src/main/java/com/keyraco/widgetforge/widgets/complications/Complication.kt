package com.keyraco.widgetforge.widgets.complications

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Box
import androidx.glance.layout.padding
import com.keyraco.widgetforge.common.model.WidgetComplicationType
import com.keyraco.widgetforge.data.model.WidgetComplication

@Composable
fun Complication(
    modifier: GlanceModifier = GlanceModifier,
    complication: WidgetComplication?,
    cellWidth: Int = 1
) {
    Box(
        modifier = modifier
            .padding(8.dp)
    ) {
        when (complication?.type) {
            WidgetComplicationType.DATE -> {
                DateComplication(complication, cellWidth)
            }
            WidgetComplicationType.BATTERY -> {
                BatteryComplication(complication, cellWidth)
            }
            else -> {}
        }
    }
}