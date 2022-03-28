package com.example.kumparanapp.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @ColumnInfo(name = "id") var Id: Int?,
    @ColumnInfo(name = "name") var Name: String?,
    @ColumnInfo(name = "username") var Username: String?,
    @ColumnInfo(name = "email") var Email: String?,
    @ColumnInfo(name = "street") var Street: String?,
    @ColumnInfo(name = "suite") var Suite: String?,
    @ColumnInfo(name = "city") var City: String?,
    @ColumnInfo(name = "zipcode") var Zipcode: String?,
    @ColumnInfo(name = "lat") var Lat: String?,
    @ColumnInfo(name = "lng") var Lng: String?,
    @ColumnInfo(name = "phone") var Phone: String?,
    @ColumnInfo(name = "website") var Website : String?,
    @ColumnInfo(name = "company_name") var CompanyName : String?,
    @ColumnInfo(name = "catchPhrase") var CatchPhrase : String?,
    @ColumnInfo(name = "bs") var Bs : String?,
) {
    @PrimaryKey(autoGenerate = true)
    var no: Int? = null
}