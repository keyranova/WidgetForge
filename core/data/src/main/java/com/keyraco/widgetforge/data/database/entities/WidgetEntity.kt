package com.keyraco.widgetforge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "Widget")
data class WidgetEntity(
    var name: String,
    var size: String,
    var config: String?,
    @PrimaryKey val id: UUID,
)