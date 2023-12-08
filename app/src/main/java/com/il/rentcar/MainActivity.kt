package com.il.rentcar

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.il.rentcar.data.DummyCar
import com.il.rentcar.databinding.ActivityMainBinding
import com.il.rentcar.model.Car
import com.il.rentcar.util.SessionManager
import com.il.rentcar.util.UserRepository
import com.il.rentcar.view.adapter.CarAdapter
import com.il.rentcar.view.setting.SettingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: UserRepository
    private var menuSetting: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionUser()

        val listCar = DummyCar.dummyCar
        setupRecycler(listCar)

    }

    private fun sessionUser() {
        val session = SessionManager(this@MainActivity)
        repository = UserRepository.getInstance(session)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar.apply {
            if (repository.getUser() != null) {
                title = "Halo, ${repository.getUser()}"
            } else {
                title = getString(R.string.app_name)
            }
        }
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.option_menu, menu)
                menuSetting = menu
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.setting -> {
                        SettingActivity.start(this@MainActivity)
                        true
                    }
                    else -> false
                }
            }
        })
    }

    private fun setupRecycler(data: List<Car>){
        val layout = GridLayoutManager(this,2)

        binding.rvCar.apply {
            layoutManager = layout
            setHasFixedSize(true)
            adapter = CarAdapter(data)
        }
    }
}