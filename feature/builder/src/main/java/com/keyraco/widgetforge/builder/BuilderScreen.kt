package com.keyraco.widgetforge.builder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyraco.widgetforge.builder.ui.SmallItemRow
import com.keyraco.widgetforge.common.model.WidgetSize
import com.keyraco.widgetforge.ui.LocalNavController
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuilderScreen(
    id: UUID
) {
    val viewModel: BuilderScreenVM = viewModel()
    val navController = LocalNavController.current
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getWidget(id)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(viewModel.widget?.name ?: "Widget")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController?.navigateUp()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                viewModel.widget?.config?.rows?.mapIndexed { index, item ->
                    when (viewModel.widget?.size) {
                        WidgetSize.SMALL -> SmallItemRow(
                            widgetRow = item,
                            setWidgetRowType = { widgetRowType ->
                                viewModel.setRowType(index, widgetRowType)
                            },
                            addComplication = { complication ->
                                viewModel.addComplication(index, complication)
                            }
                        )
                        else -> null
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.saveWidget()
                }
            ) {
                Text("Save")
            }
        }
    }
}
