package com.example.android_mvc.network

import com.example.android_mvc.network.service.CardService
import com.example.android_mvc.network.service.UserService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private val IS_TESTER = true
    private val SERVER_DEVELOPMENT = "http://10.0.2.2:8080/"
    private val SERVER_PRODUCTION = "http://10.0.2.2:8080/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val userService: UserService = retrofit.create(UserService::class.java)
    val cardService: CardService = retrofit.create(CardService::class.java)
}




