package com.keyraco.widgetforge.builder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyraco.widgetforge.common.model.WidgetRowType
import com.keyraco.widgetforge.data.model.Widget
import com.keyraco.widgetforge.data.model.WidgetComplication
import com.keyraco.widgetforge.data.repositories.WidgetRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

class BuilderScreenVM : ViewModel(), KoinComponent {
    private val widgetRepository: WidgetRepository by inject()

    var widget by mutableStateOf<Widget?>(null)

    fun getWidget(id: UUID) {
        viewModelScope.launch {
            widget = widgetRepository.getOne(id).first()
        }
    }

    fun setRowType(rowIndex: Int, widgetRowType: WidgetRowType) {
        if (widget != null) {
            val rows = (widget!!.config.rows ?: emptyList()).toMutableStateList()
            val row = rows[rowIndex]

            rows[rowIndex] = row.copy(type = widgetRowType)

            widget = widget!!.copy(config = widget!!.config.copy(rows = rows))
        }
    }

    fun addComplication(rowIndex: Int, complication: WidgetComplication) {
        if (widget != null) {
            val rows = (widget!!.config.rows ?: emptyList()).toMutableStateList()
            val row = rows[rowIndex]

            val complications = (row.complications ?: emptyList()).toMutableStateList()
            complications.add(complication)

            rows[rowIndex] = row.copy(complications = complications)

            widget = widget!!.copy(config = widget!!.config.copy(rows = rows))
        }
    }

    fun saveWidget() {
        if (widget != null) {
            widgetRepository.update(widget!!)
        }
    }
}