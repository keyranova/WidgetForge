package com.keyraco.widgetforge.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.keyraco.widgetforge.data.database.entities.WidgetEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface WidgetDao {
    @Query("SELECT * FROM Widget LIMIT :limit OFFSET :offset")
    fun query(limit: Int, offset: Int): Flow<List<WidgetEntity>>

    @Query("SELECT * FROM Widget WHERE id = :id")
    fun queryFirst(id: UUID): Flow<WidgetEntity>

    @Insert
    suspend fun insert(widget: WidgetEntity)

    @Insert
    suspend fun insert(widgets: List<WidgetEntity>)

    @Update
    suspend fun update(widget: WidgetEntity)

    @Update
    suspend fun update(widgets: List<WidgetEntity>)

    @Query("DELETE FROM Widget WHERE id = :id")
    suspend fun delete(id: UUID)

    @Query("DELETE FROM WIDGET WHERE id IN (:ids)")
    suspend fun delete(ids: List<UUID>)
}