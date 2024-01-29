package com.example.recyclerview.api

import com.example.recyclerview.model.MealList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php")
    suspend fun fetchData(@Query("s") query: String): Response<MealList>
}
