package com.keyraco.widgetforge.widgets.widgetpicker

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyraco.widgetforge.widgets.SmallWidget
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetPicker(
    context: Context,
    id: Int,
    onSelect: () -> Unit
) {
    val viewModel: WidgetPickerVM = viewModel()
    val scope = rememberCoroutineScope()
    val manager = GlanceAppWidgetManager(context)
    val glanceID = manager.getGlanceIdBy(id)

    val widgets by remember(viewModel) {
        viewModel.getWidgets()
    }.collectAsStateWithLifecycle(null)

    LaunchedEffect(Unit) {
        if (id == 0) {
            onSelect()
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Select a Widget")}
            )
        }
    ) { innerPadding ->
        if (!widgets.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            ) {
                widgets?.let {
                    items(it) { widget ->
                        WidgetPickerItem(
                            widget = widget,
                            onSelect = {
                                viewModel.selectWidget(widget.id, glanceID.toString())

                                scope.launch {
                                    updateAppWidgetState(context, glanceID) {
                                        it[longPreferencesKey("now")] = System.currentTimeMillis()
                                    }
                                    SmallWidget().update(context, glanceID)
                                }

                                onSelect()
                            }
                        )
                    }
                }
            }
        }
    }
}