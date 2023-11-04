package com.fahmiproduction.githubappcleanarch

import android.app.Application
import com.fahmiproduction.githubappcleanarch.core.di.databaseModule
import com.fahmiproduction.githubappcleanarch.core.di.networkModule
import com.fahmiproduction.githubappcleanarch.core.di.repositoryModule
import com.fahmiproduction.githubappcleanarch.di.useCaseModule
import com.fahmiproduction.githubappcleanarch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}