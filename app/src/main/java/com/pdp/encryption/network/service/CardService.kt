package com.example.android_mvc.network.service

import com.example.android_advanced_kotlin.activity.model.Card
import com.example.android_advanced_kotlin.activity.model.User
import retrofit2.Call
import retrofit2.http.*

interface CardService {

    @Headers("Content-type:application/json")

    @GET("api/cards")
    fun getCards(): Call<ArrayList<Card>>

    @GET("api/cards/{id}")
    fun getCardById(@Path("id") id: Int): Call<Card>

    @POST("api/cards")
    fun createCard(@Body card: Card): Call<Card>

}