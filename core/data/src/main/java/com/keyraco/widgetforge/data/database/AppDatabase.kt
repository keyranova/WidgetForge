package com.keyraco.widgetforge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.keyraco.widgetforge.data.database.daos.AssignedWidgetDao
import com.keyraco.widgetforge.data.database.daos.WidgetDao
import com.keyraco.widgetforge.data.database.entities.AssignedWidgetEntity
import com.keyraco.widgetforge.data.database.entities.WidgetEntity

@Database(
    entities = [
        WidgetEntity::class,
        AssignedWidgetEntity::class
    ], version = 2, exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun widgetDao(): WidgetDao
    abstract fun assignedWidgetDao(): AssignedWidgetDao

    companion object {
        private var _instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            val instance = _instance
                ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "room")
                    .addMigrations(MIGRATION_1_2)
                    .build()
            if (_instance == null) _instance = instance
            return instance
        }
    }
}