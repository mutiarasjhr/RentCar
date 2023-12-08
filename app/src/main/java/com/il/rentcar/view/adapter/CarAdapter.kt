package com.il.rentcar.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.il.rentcar.R
import com.il.rentcar.databinding.ItemCarBinding
import com.il.rentcar.model.Car
import com.il.rentcar.util.Extensions.showImage
import com.il.rentcar.view.detail.DetailActivity

class CarAdapter(private val data: List<Car>
): RecyclerView.Adapter<CarAdapter.ViewHolder>(){

    inner class ViewHolder(var binding: ItemCarBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = data[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(car.imageUrl)
                .into(ivCar)
            tvName.text = car.name
            tvBrand.text = car.brand
            tvPerson.text = holder.itemView.context.getString(R.string.orang, car.capacity.toString())
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_CAR, car)
            holder.itemView.context.startActivity(intent)
        }
    }
}