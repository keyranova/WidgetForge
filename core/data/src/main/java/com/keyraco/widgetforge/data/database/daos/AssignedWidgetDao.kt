package com.keyraco.widgetforge.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.keyraco.widgetforge.data.database.entities.AssignedWidgetEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface AssignedWidgetDao {
    @Query("SELECT widgetId FROM AssignedWidget WHERE glanceId = :id")
    fun getWidgetId(id: String): Flow<UUID?>

    @Insert
    suspend fun insert(assignedWidget: AssignedWidgetEntity)

    @Update
    suspend fun update(assignedWidget: AssignedWidgetEntity)

    @Query("DELETE FROM AssignedWidget WHERE glanceId = :id")
    suspend fun delete(id: String)
}