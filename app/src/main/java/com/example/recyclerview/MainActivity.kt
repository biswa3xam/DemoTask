package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.MealListAdapter
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.api.DataRepository
import com.example.recyclerview.api.RetrofitClient
import com.example.recyclerview.viewmodel.MealListViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MealListViewModel by viewModels {
        MealListViewModel.Factory(DataRepository(RetrofitClient.apiService))
    }

    private lateinit var mealListAdapter: MealListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealListAdapter = MealListAdapter(this, emptyList())
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity);
            recyclerView.adapter = mealListAdapter
        }

        viewModel.mealDetails.observe(this) { mealDetails ->
            mealListAdapter.updateData(mealDetails.meals)
        }
        viewModel.fetchData("Chicken")
    }
}
