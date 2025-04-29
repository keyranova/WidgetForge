package com.keyraco.widgetforge.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `AssignedWidget` (`widgetId` BLOB NOT NULL, `glanceId` TEXT NOT NULL, PRIMARY KEY(`widgetId`, `glanceId`))")
    }
}