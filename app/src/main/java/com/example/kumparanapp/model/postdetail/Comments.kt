package com.example.kumparanapp.model.postdetail

import com.example.kumparanapp.model.user.Geo

data class Comments(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)