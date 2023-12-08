package com.il.rentcar.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.il.rentcar.MainActivity
import com.il.rentcar.R
import com.il.rentcar.databinding.ActivityLoginBinding
import com.il.rentcar.util.SessionManager
import com.il.rentcar.util.UserRepository

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionUser()
        loginButton()
    }

    private fun loginButton() {
        binding.btnLogin.setOnClickListener {
            saveSession()
        }
    }

    private fun sessionUser() {
        val session = SessionManager(this@LoginActivity)
        repository = UserRepository.getInstance(session)

        if (repository.isUserLogin()) {
            login()
        }
    }

    private fun login() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveSession() {
        repository.loginUser(binding.edUsername.text.toString())
        login()
    }
}