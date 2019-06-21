package com.example.geniusplazausers.networking

import com.example.geniusplazausers.models.UserPagedResult
import com.example.geniusplazausers.models.UserPostResponse
import retrofit2.http.*

interface UserApi{

    @GET("/api/users")
    suspend fun getUsers(@Query("page") page : Int): UserPagedResult

    @FormUrlEncoded
    @POST("/api/users")
    suspend fun addUser(@Field("name") name: String, @Field("job") job : String) : UserPostResponse
}