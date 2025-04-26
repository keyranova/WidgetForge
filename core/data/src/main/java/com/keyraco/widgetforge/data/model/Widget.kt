package com.keyraco.widgetforge.data.model

import com.keyraco.widgetforge.common.ktx.decodeFromStringOrNull
import com.keyraco.widgetforge.common.model.WidgetComplicationType
import com.keyraco.widgetforge.common.model.WidgetRowType
import com.keyraco.widgetforge.common.model.WidgetSize
import com.keyraco.widgetforge.data.database.entities.WidgetEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.UUID

@Serializable
data class WidgetComplication(
    val type: WidgetComplicationType,
    val position: Int
)

@Serializable
data class WidgetRow(
    var type: WidgetRowType,
    var complications: List<WidgetComplication>? = null
)

@Serializable
data class WidgetConfig(
    var rows: List<WidgetRow>? = null
)

data class Widget(
    val id: UUID,
    val name: String,
    val size: WidgetSize = WidgetSize.SMALL,
    var config: WidgetConfig = WidgetConfig()
) {
    fun toDatabaseEntity(): WidgetEntity {
        return WidgetEntity(
            id = id,
            name = name,
            size = size.toString(),
            config = Json.encodeToString(config)
        )
    }

    companion object {
        fun fromDatabaseEntity(entity: WidgetEntity): Widget {
            val config: WidgetConfig =
                Json.decodeFromStringOrNull(entity.config?.takeIf { it.isNotBlank() })
                    ?: WidgetConfig()
            return Widget(entity.id, entity.name, WidgetSize.valueOf(entity.size), config)
        }
    }
}