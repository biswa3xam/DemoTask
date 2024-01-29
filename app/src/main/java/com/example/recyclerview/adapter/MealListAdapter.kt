package com.example.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerview.R
import com.example.recyclerview.model.Meal
import com.example.recyclerview.DetailActivity // Import your detail activity

class MealListAdapter(private val context: Context, private var dataList: List<Meal>) : RecyclerView.Adapter<MealListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val meal = dataList[position]
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("mealName", meal.strMeal)
                    putExtra("mealImageUrl", meal.strMealThumb)
                    putExtra("description",meal.strInstructions)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = dataList[position]
        holder.textView.text = meal.strMeal
        Glide.with(context)
            .load(meal.strMealThumb)
            .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newData: List<Meal>) {
        dataList = newData
        notifyDataSetChanged()
    }
}

