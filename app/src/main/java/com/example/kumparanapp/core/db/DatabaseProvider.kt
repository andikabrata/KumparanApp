package com.example.kumparanapp.core.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kumparanapp.core.db.AppDatabase.Companion.DATABASE_NAME

class DatabaseProvider(private val context: Context) {

    private var database: AppDatabase? = null

    /**
     * Gets an instance of [AppDatabase].
     *
     * @return an instance of [AppDatabase]
     */
    fun getInstance(): AppDatabase =
        database ?: synchronized(this) {
            database ?: buildDatabase().also { database = it }
        }

    private fun buildDatabase(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .addCallback(onCreateDatabase())
            .fallbackToDestructiveMigration()
            .build()

    private fun onCreateDatabase() = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}