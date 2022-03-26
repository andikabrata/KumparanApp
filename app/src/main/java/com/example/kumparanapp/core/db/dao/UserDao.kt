package com.example.kumparanapp.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kumparanapp.core.db.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id IN (:userId) ")
    fun getUserById(userId: String): LiveData<List<UserEntity>>

    @Query("SELECT id FROM user")
    fun getIdUserAll(): LiveData<List<UserEntity>>

    @Insert
    fun insert(vararg records: UserEntity)

    @Delete
    fun delete(record: UserEntity)

    @Query("DELETE FROM user WHERE id IN (:ids)")
    fun deleteItemByIds(ids: Int)

    @Query("DELETE FROM user")
    fun deleteAll()
}