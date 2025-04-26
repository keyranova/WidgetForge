package com.keyraco.widgetforge.home.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyraco.widgetforge.common.Screens
import com.keyraco.widgetforge.data.model.Widget
import com.keyraco.widgetforge.ui.LocalNavController

@Composable
fun WidgetPreview(
    widget: Widget
) {
    val viewModel: WidgetPreviewVM = viewModel()
    val navController = LocalNavController.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            navController?.navigate(Screens.BUILDER.route.replace("{id}", widget.id.toString()))
        }
    ) {
        Row {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = widget.name
            )
            IconButton(
                onClick = {
                    viewModel.delete(widget)
                }
            ) {
                Icon(Icons.Rounded.Delete, "delete widget")
            }
        }
    }
}