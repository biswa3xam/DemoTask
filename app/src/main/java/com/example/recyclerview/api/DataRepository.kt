package com.example.recyclerview.api


import com.example.recyclerview.model.MealList
import retrofit2.Response

class DataRepository(private val apiService: ApiService) {
    suspend fun fetchData(string: String): Response<MealList> {
        return apiService.fetchData(string)
    }
}