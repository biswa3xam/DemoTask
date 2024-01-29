package com.example.recyclerview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview.model.MealList
import com.example.recyclerview.api.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MealListViewModel(private val repository: DataRepository) : ViewModel() {

    private val _mealDetails = MutableLiveData<MealList>()
    val mealDetails: LiveData<MealList> = _mealDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchData(string: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<MealList> = repository.fetchData(string)

                withContext(Dispatchers.Main) {

                    if (response.isSuccessful) {
                        Log.d("mohan", "api data is  ${response.body()}")
                        _mealDetails.value = response.body()
                    } else {
                        _error.value = "Error: ${response.code()}"
                        Log.d("mohan", " 1 ${response.code()}")
                    }

                }
            } catch (e: Exception) {
                _error.value = "Network error: ${e.message}"
                Log.d("mohan", " 2 ${e.message}")

            }
        }
    }

    class Factory(private val repository: DataRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MealListViewModel(repository) as T
        }
    }
}