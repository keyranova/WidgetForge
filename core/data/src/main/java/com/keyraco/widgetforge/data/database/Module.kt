package com.keyraco.widgetforge.data.database

import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }
}