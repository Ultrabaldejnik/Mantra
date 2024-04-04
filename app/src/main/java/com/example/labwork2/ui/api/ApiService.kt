package com.example.labwork2.ui.api


import retrofit2.http.*

interface RetrofitServices {
    @GET("marvel")
    suspend fun getMovieList(): List<SuperHeroModelApi>
}