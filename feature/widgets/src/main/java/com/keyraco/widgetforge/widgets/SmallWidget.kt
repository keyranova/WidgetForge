package com.keyraco.widgetforge.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import com.keyraco.widgetforge.common.model.SmallWidgetDimensions
import com.keyraco.widgetforge.data.model.Widget
import com.keyraco.widgetforge.data.repositories.WidgetRepository
import com.keyraco.widgetforge.widgets.widgetpicker.WidgetPickerActivity
import kotlinx.coroutines.launch
import org.koin.androidx.compose.inject

class SmallWidget : GlanceAppWidget() {

    override var stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override val sizeMode = SizeMode.Responsive(
        setOf(
            SmallWidgetDimensions.SMALL_MIN.size,
            SmallWidgetDimensions.SMALL_MAX.size
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            MyContent(
                context = context,
                id = id
            )
        }
    }

    @Composable
    private fun MyContent(context: Context, id: GlanceId) {
        val widgetRepository: WidgetRepository by inject()
        val scope = rememberCoroutineScope()

        var widget by remember { mutableStateOf<Widget?>(null) }

        val preferences = currentState<Preferences>()
        val updatedAt = preferences[longPreferencesKey(
            "now"
        )]

        LaunchedEffect(updatedAt) {
            scope.launch {
                widgetRepository.getWidgetByGlanceId(id).collect {
                    widget = it
                }
            }
        }

        if (widget != null) {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                
            }
        } else {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .clickable(
                        onClick = actionStartActivity<WidgetPickerActivity>(
                            actionParametersOf(ActionParameters.Key<Int>("id") to id.toAppWidgetId())
                        )
                    ),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Tap here to select a widget", modifier = GlanceModifier.padding(12.dp))
            }
        }
    }
}

fun GlanceId.toAppWidgetId(): Int {
    val appWidgetId = this.toString().filter { it.isDigit() }.toInt()
    return appWidgetId
}