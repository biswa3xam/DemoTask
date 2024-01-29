package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerview.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val mealName = intent.getStringExtra("mealName")
            val mealImageUrl = intent.getStringExtra("mealImageUrl")
            val mealDescription = intent.getStringExtra("description")

            foodItem.text = mealName
            contentDescription.text = mealDescription

            Glide.with(this@DetailActivity)
                .load(mealImageUrl)
                .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(foodItemImage)

        }
    }
}
