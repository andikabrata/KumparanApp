package com.example.kumparanapp.core.api

import com.example.kumparanapp.model.post.Posts
import com.example.kumparanapp.model.user.User
import retrofit2.http.GET

interface ServiceApi {
    @GET("users")
    suspend fun getUser(): List<User>

    @GET("posts")
    suspend fun getPost(): List<Posts>
}