package com.keyraco.widgetforge.data.model

import com.keyraco.widgetforge.data.database.entities.AssignedWidgetEntity
import java.util.UUID

data class AssignedWidget(
    val id: UUID,
    val glanceId: String,
) {
    fun toDatabaseEntity(): AssignedWidgetEntity {
        return AssignedWidgetEntity(
            widgetId = id,
            glanceId = glanceId
        )
    }

    companion object {
        fun fromDatabaseEntity(entity: AssignedWidgetEntity): AssignedWidget {
            return AssignedWidget(entity.widgetId, entity.glanceId)
        }
    }
}