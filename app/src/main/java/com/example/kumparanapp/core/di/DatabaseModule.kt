package com.example.kumparanapp.core.di

import com.example.kumparanapp.core.db.DaoProvider
import com.example.kumparanapp.core.db.DatabaseProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseProvider(androidContext()) }
    single { DaoProvider(get()) }
}