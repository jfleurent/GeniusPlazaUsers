package com.example.geniusplazausers

import retrofit2.http.*

interface UserApi{

    @GET("/api/users")
    suspend fun getUsers(@Query("page") page : Int): UserPagedResult

    @FormUrlEncoded
    @POST("/api/users")
    suspend fun addUser(@Field("name") name: String, @Field("job") job : String) : UserPostResponse
}