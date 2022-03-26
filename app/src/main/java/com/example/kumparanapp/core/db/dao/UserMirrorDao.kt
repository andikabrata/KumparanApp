package com.example.kumparanapp.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kumparanapp.core.db.entity.UserMirrorEntity

@Dao
interface UserMirrorDao {
    @Query("SELECT * FROM usermirror")
    fun getAll(): LiveData<List<UserMirrorEntity>>

    @Query("SELECT * FROM usermirror WHERE id IN (:userId) ")
    fun getUserById(userId: String): LiveData<List<UserMirrorEntity>>

    @Query("SELECT id FROM usermirror")
    fun getIdUserAll(): LiveData<List<UserMirrorEntity>>

    @Insert
    fun insert(vararg records: UserMirrorEntity)

    @Delete
    fun delete(record: UserMirrorEntity)

    @Query("DELETE FROM usermirror WHERE id IN (:ids)")
    fun deleteItemByIds(ids: Int)

    @Query("DELETE FROM usermirror WHERE id IN (:no)")
    fun deleteItemByNoUrut(no: Int)

    @Query("DELETE FROM usermirror")
    fun deleteAll()
}