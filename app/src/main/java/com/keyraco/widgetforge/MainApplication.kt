package com.keyraco.widgetforge

import android.app.Application
import com.keyraco.widgetforge.data.database.databaseModule
import com.keyraco.widgetforge.data.repositories.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule
                )
            )
        }
    }
}