package br.com.podesenvolver.data.local

import android.app.Application
import androidx.room.Room

class DatabaseConfig {
    companion object {
        fun create(application: Application) =
            Room.databaseBuilder(application, AppDatabase::class.java, "podesenvolver-database")
                .build()
    }
}
