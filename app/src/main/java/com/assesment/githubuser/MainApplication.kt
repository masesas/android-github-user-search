package com.assesment.githubuser

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            workManagerFactory()
            modules(appModule)
        }

        val currentDBPath = getDatabasePath("github_user_db.db").absolutePath
        println("Current DB Path: $currentDBPath")

    }
}