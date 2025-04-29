package com.keyraco.widgetforge.data.database.entities

import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "AssignedWidget", primaryKeys = ["widgetId", "glanceId"])
data class AssignedWidgetEntity(
    var widgetId: UUID,
    var glanceId: String,
)

data class PartialAssignedWidgetEntity(
    var widgetId: UUID
)