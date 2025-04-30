package com.keyraco.widgetforge.builder.ui.complications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.keyraco.widgetforge.common.model.WidgetComplicationType
import com.keyraco.widgetforge.data.model.WidgetComplication

@Composable
fun RowScope.Complication(
    modifier: Modifier = Modifier,
    complication: WidgetComplication,
    cellWidth: Int = 1
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .weight(1f)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small),
    ) {
        when (complication.type) {
            WidgetComplicationType.DATE -> {
                DateComplication(complication, cellWidth)
            }
            else -> {
                Text(
                    text = complication.type.toString(),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}