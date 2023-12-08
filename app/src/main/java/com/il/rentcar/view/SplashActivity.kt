package com.il.rentcar.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.example.githubuser.data.preferences.SettingPreferences
import com.example.githubuser.data.preferences.SettingViewModelFactory
import com.example.githubuser.data.preferences.dataStore
import com.il.rentcar.MainActivity
import com.il.rentcar.databinding.ActivitySplashBinding
import com.il.rentcar.util.isDarkModeOn
import com.il.rentcar.view.login.LoginActivity
import com.il.rentcar.view.setting.SettingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setSplash()

        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel by viewModels<SettingViewModel> { SettingViewModelFactory(pref) }
        settingViewModel.getThemeSettings(isDarkModeOn())
            .observe(this) { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                setContentView(binding.root)
            }
    }

    private fun setSplash() {
        supportActionBar?.hide()
        scope.launch {
            delay(1000L)
            runOnUiThread {
                val intent = Intent(this@SplashActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}