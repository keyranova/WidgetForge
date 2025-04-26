package com.keyraco.widgetforge.data.repositories

import org.koin.dsl.module

val repositoryModule = module {
    factory { WidgetRepository(get()) }
}