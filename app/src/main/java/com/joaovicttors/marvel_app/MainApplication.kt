package com.joaovicttors.marvel_app

import android.app.Application
import com.joaovicttors.marvel_app.di.CharacterModule
import com.joaovicttors.marvel_app.di.ProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class MainApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initInjections()
    }

    private fun initInjections() {
        startKoin { ->
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)

            modules(allModules())
        }
    }

    private fun allModules(): List<Module> = listOf(
        ProviderModule.module,
        *CharacterModule.allModules,
    )
}