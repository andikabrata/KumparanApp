package com.example.kumparanapp.core.api

import com.example.kumparanapp.model.photo.Photo
import com.example.kumparanapp.model.post.Posts
import com.example.kumparanapp.model.postdetail.Comments
import com.example.kumparanapp.model.user.User
import com.example.kumparanapp.model.userdetail.Album
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("users")
    suspend fun getUser(): List<User>

    @GET("posts")
    suspend fun getPost(): List<Posts>

    @GET("comments")
    suspend fun getListComment(
        @Query("postId") apiKey: String?
    ): List<Comments>

    @GET("albums")
    suspend fun getAlbum(
        @Query("userId") apiKey: String?
    ): List<Album>

    @GET("photos")
    suspend fun getPhoto(
        @Query("albumId") apiKey: String?
    ): List<Photo>
}