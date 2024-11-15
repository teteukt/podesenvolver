package br.com.podesenvolver

import android.app.Application
import br.com.podesenvolver.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppInitialization: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppInitialization)
            modules(appModules)
        }
    }
}