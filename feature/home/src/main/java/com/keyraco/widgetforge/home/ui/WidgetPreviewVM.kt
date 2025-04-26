package com.keyraco.widgetforge.home.ui

import androidx.lifecycle.ViewModel
import com.keyraco.widgetforge.data.model.Widget
import com.keyraco.widgetforge.data.repositories.WidgetRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WidgetPreviewVM : ViewModel(), KoinComponent {
    private val widgetRepository: WidgetRepository by inject()

    fun delete(widget: Widget) {
        widgetRepository.delete(widget)
    }
}