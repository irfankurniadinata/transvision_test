package com.example.test.core

import android.app.Application
import android.content.Context
import android.content.Intent
import com.example.test.di.networkModule
import com.example.test.di.repositoryModule
import com.example.test.di.useCaseModule
import com.example.test.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        mInstance = this

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

    }

    companion object {

        private var mInstance: App? = null

        fun getContext(): Context? {
            return mInstance?.applicationContext
        }
    }
}