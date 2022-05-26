package com.example.android_mvc.network.service

import com.example.android_advanced_kotlin.activity.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @Headers("Content-type:application/json")

    @GET("api/users")
    fun getUsers(): Call<ArrayList<User>>

    @GET("api/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("api/users")
    fun createUser(@Body user: User): Call<User>

}