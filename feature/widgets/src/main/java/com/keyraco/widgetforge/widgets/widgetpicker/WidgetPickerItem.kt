package com.keyraco.widgetforge.widgets.widgetpicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keyraco.widgetforge.data.model.Widget

@Composable
fun WidgetPickerItem(
    widget: Widget,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {
                onSelect()
            })
    ) {
        Row {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = widget.name
            )
        }
    }
}