package com.keyraco.widgetforge.builder.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keyraco.widgetforge.common.model.WidgetComplicationType
import com.keyraco.widgetforge.ui.modifiers.dashedBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.AddComplicationButton(
    modifier: Modifier = Modifier,
    addComplication: (complicationType: WidgetComplicationType) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    IconButton(
        modifier = modifier
            .fillMaxHeight()
            .weight(1f)
            .dashedBorder(Color.LightGray, MaterialTheme.shapes.small, 2.dp),
        onClick = { showBottomSheet = true }
    ) {
        Icon(Icons.Rounded.Add, "add complication")
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            WidgetComplicationType.entries.forEach { type ->
                Button(
                    onClick = {
                        addComplication(type)
                    }
                ) {
                    Text(type.toString())
                }
            }
        }
    }
}