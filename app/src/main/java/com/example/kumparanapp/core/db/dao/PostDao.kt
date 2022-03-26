package com.example.kumparanapp.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kumparanapp.core.db.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM post WHERE userId IN (:userID)")
    fun getAllByUserId(userID: String): LiveData<List<PostEntity>>

    @Insert
    fun insert(vararg records: PostEntity)

    @Delete
    fun delete(record: PostEntity)

    @Query("DELETE FROM post WHERE id IN (:ids)")
    fun deleteItemByIds(ids: Int)

    @Query("DELETE FROM post")
    fun deleteAll()

}