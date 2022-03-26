package com.example.kumparanapp.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    @ColumnInfo(name = "userId") var userID: Int?,
    @ColumnInfo(name = "id") var Id: Int?,
    @ColumnInfo(name = "title") var Title: String?,
    @ColumnInfo(name = "body") var Body: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var no: Int? = null
}