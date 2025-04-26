package com.keyraco.widgetforge.home

import androidx.lifecycle.ViewModel
import com.keyraco.widgetforge.common.model.WidgetRowType
import com.keyraco.widgetforge.common.model.WidgetSize
import com.keyraco.widgetforge.data.model.Widget
import com.keyraco.widgetforge.data.model.WidgetConfig
import com.keyraco.widgetforge.data.model.WidgetRow
import com.keyraco.widgetforge.data.repositories.WidgetRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

class HomeScreenVM : ViewModel(), KoinComponent {
    private val widgetRepository: WidgetRepository by inject()

    fun getWidgets(): Flow<List<Widget>> {
        return widgetRepository.get(
            limit = 9999,
            offset = 0
        )
    }

    fun createWidget(size: WidgetSize): UUID {
        val id = UUID.randomUUID()
        val widget = Widget(
            id = id,
            name = "${size.toString()} Widget",
            size = size,
            config = when (size) {
                WidgetSize.SMALL -> WidgetConfig(rows = listOf(
                    WidgetRow(type = WidgetRowType.FULL_TWO_WIDE),
                    WidgetRow(type = WidgetRowType.FULL_TWO_WIDE)
                ))
            }
        )

        widgetRepository.create(widget)

        return id
    }
}