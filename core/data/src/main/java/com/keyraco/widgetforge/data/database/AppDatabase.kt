package com.keyraco.widgetforge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.keyraco.widgetforge.data.database.daos.WidgetDao
import com.keyraco.widgetforge.data.database.entities.WidgetEntity

@Database(
    entities = [
        WidgetEntity::class,
    ], version = 1, exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun widgetDao(): WidgetDao

    companion object {
        private var _instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            val instance = _instance
                ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "room")
                    .build()
            if (_instance == null) _instance = instance
            return instance
        }
    }
}