package com.example.geniusplazausers

import retrofit2.http.*

interface UserApi{

    @GET("/api/users")
    suspend fun getUsers(@Query("page") page : Int): List<User>

    @POST("/api/users")
    fun addUser(@Body user : User)
}