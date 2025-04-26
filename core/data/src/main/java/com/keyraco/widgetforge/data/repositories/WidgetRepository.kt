package com.keyraco.widgetforge.data.repositories

import com.keyraco.widgetforge.data.database.AppDatabase
import com.keyraco.widgetforge.data.model.Widget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
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

    fun delete(widget: Widget) {
        val dao = database.widgetDao()
        scope.launch {
            dao.delete(widget.id)
        }
    }
}