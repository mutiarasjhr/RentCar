package com.il.rentcar.view.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import com.bumptech.glide.Glide
import com.il.rentcar.R
import com.il.rentcar.databinding.ActivityDetailBinding
import com.il.rentcar.model.Car

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var menuSetting: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setData()
    }

    private fun setData(){
        val data = intent.getParcelableExtra<Car>(EXTRA_CAR)

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(data?.imageUrl)
                .into(ivDetailCar)

            tvDetailName.text = data?.name
            tvDetailBrand.text = data?.brand
            tvDetailPrice.text = data?.harga
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail"
        }
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        onBackPressedDispatcher.onBackPressed()
                        true
                    }
                    else -> false
                }
            }
        })
    }

    companion object {
        const val EXTRA_CAR = "extra_car"
    }
}