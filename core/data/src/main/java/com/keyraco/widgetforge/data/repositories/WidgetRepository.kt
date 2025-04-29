package com.keyraco.widgetforge.data.repositories

import androidx.glance.GlanceId
import com.keyraco.widgetforge.data.database.AppDatabase
import com.keyraco.widgetforge.data.database.entities.AssignedWidgetEntity
import com.keyraco.widgetforge.data.model.Widget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

class WidgetRepository(
    private val database: AppDatabase
) {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)

    fun get(limit: Int, offset: Int): Flow<List<Widget>> {
        val dao = database.widgetDao()
        return dao.query(limit, offset).map {
            it.map { entity ->
                Widget.fromDatabaseEntity(entity)
            }
        }
    }

    fun getOne(id: UUID): Flow<Widget> {
        val dao = database.widgetDao()
        return dao.queryFirst(id).map {
            Widget.fromDatabaseEntity(it)
        }
    }

    suspend fun getWidgetByGlanceId(id: GlanceId): Flow<Widget?> {
        val dao = database.assignedWidgetDao()
        val widgetDao = database.widgetDao()

        val assignedWidgetFlow = dao.getWidgetId(id.toString())

        val widgetId = assignedWidgetFlow.first()

        if (widgetId != null) {
            return widgetDao.queryFirst(widgetId).map {
                Widget.fromDatabaseEntity(it)
            }
        }

        return flowOf()
    }

    fun update(widget: Widget) {
        val dao = database.widgetDao()
        scope.launch {
            dao.update(widget.toDatabaseEntity())
        }
    }

    fun create(widget: Widget) {
        val dao = database.widgetDao()
        scope.launch {
            dao.insert(widget.toDatabaseEntity())
        }
    }

    fun createAssignedWidget(widgetId: UUID, glanceId: String) {
        val dao = database.assignedWidgetDao()
        scope.launch {
            dao.insert(AssignedWidgetEntity(
                widgetId = widgetId,
                glanceId = glanceId
            ))
        }
    }

    fun delete(widget: Widget) {
        val dao = database.widgetDao()
        scope.launch {
            dao.delete(widget.id)
        }
    }
}