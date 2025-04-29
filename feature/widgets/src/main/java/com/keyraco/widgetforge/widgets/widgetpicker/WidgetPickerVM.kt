package com.keyraco.widgetforge.widgets.widgetpicker

import androidx.lifecycle.ViewModel
import com.keyraco.widgetforge.data.model.Widget
import com.keyraco.widgetforge.data.repositories.WidgetRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

class WidgetPickerVM : ViewModel(), KoinComponent {
    private val widgetRepository: WidgetRepository by inject()

    fun getWidgets(): Flow<List<Widget>> {
        return widgetRepository.get(
            limit = 9999,
            offset = 0
        )
    }

    fun selectWidget(widgetId: UUID, glanceId: String) {
        widgetRepository.createAssignedWidget(widgetId, glanceId)
    }
}