package com.il.rentcar.util

import com.il.rentcar.util.SessionManager.Companion.KEY_USERNAME

class UserRepository(private val session: SessionManager) {

    fun loginUser(username: String) {
        session.createLoginSession()
        session.saveToPreference(KEY_USERNAME, username)
    }

    fun getUser() = session.getFromPreference(KEY_USERNAME)

    fun isUserLogin() = session.isLogin

    fun logoutUser() = session.logout()

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(session: SessionManager): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(session)
            }
    }
}