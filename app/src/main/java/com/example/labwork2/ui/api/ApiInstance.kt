package com.example.labwork2.ui.api

object ApiInstance {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}