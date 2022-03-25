package com.example.kumparanapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.kumparanapp.core.di.appComponent
import com.github.ajalt.timberkt.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class KumparanApp: Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@KumparanApp)
            modules(provideComponent())
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    /**
     * provide list of modules @see [appComponent]
     */
    private fun provideComponent(): List<Module> {
        return appComponent
    }

    companion object {
        lateinit var instance: KumparanApp
            private set
    }
}