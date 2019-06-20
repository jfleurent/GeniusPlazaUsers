package com.example.geniusplazausers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkingService {
    private lateinit var BASE_URL : String
    private lateinit var retrofit: Retrofit

    fun getUserApi() : UserApi{
        BASE_URL = "https://reqres.in"
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(UserApi::class.java)
    }
}