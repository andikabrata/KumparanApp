package com.example.kumparanapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kumparanapp.core.db.dao.PostDao
import com.example.kumparanapp.core.db.dao.UserDao
import com.example.kumparanapp.core.db.dao.UserMirrorDao
import com.example.kumparanapp.core.db.entity.PostEntity
import com.example.kumparanapp.core.db.entity.UserEntity
import com.example.kumparanapp.core.db.entity.UserMirrorEntity

@Database(entities = [
    UserEntity::class,
    PostEntity::class,
    UserMirrorEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "kumparan-db"
    }

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun userMirrorDao(): UserMirrorDao
}