package com.example.kumparanapp.core.db

import com.example.kumparanapp.core.db.dao.PostDao
import com.example.kumparanapp.core.db.dao.UserDao
import com.example.kumparanapp.core.db.dao.UserMirrorDao


/**
 * Repository with the database [androidx.room.Dao]s.
 */
class DaoProvider(database: DatabaseProvider) {

    val userDao: UserDao = database.getInstance().userDao()
    val postDao: PostDao = database.getInstance().postDao()
    val userMirrorDao: UserMirrorDao = database.getInstance().userMirrorDao()
}