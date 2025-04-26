package com.keyraco.widgetforge.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyraco.widgetforge.common.Screens
import com.keyraco.widgetforge.common.model.WidgetSize
import com.keyraco.widgetforge.home.ui.WidgetPreview
import com.keyraco.widgetforge.ui.LocalNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeScreenVM = viewModel()
    val density = LocalDensity.current
    val navController = LocalNavController.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val widgets by remember(viewModel) {
        viewModel.getWidgets()
    }.collectAsStateWithLifecycle(null)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Widget Forge")}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                }
            ) {
                Icon(Icons.Rounded.Add, "Create New Widget")
            }
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
                        WidgetPreview(widget)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            ) {
                Text("Click the button below to begin creating widgets!")
            }
        }


        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Button(
                    onClick = {
                        val id = viewModel.createWidget(WidgetSize.SMALL)
                        navController?.navigate(Screens.BUILDER.route.replace("{id}", id.toString()))
                    }
                ) {
                    Text("Small")
                }
            }
        }
    }
}