package com.example.bitebyte.utils

import android.content.Context

class SessionManager(context: Context) {
    private var pref = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    companion object {
        const val USER_NAME = "user_name"
        const val USER_TOKEN = "user_token"
        const val FILL_INPUT = "fill_input"
    }

    fun addUserSession(name: String, token: String){
        pref.edit().putString(USER_NAME, name).apply()
        pref.edit().putString(USER_TOKEN, token).apply()
    }

    fun addFillInput(fillInput: String){
        pref.edit().putString(FILL_INPUT, fillInput).apply()
    }

    fun getFillInput() : String? = pref.getString(FILL_INPUT, null)

    fun getName() : String? = pref.getString(USER_NAME, null)

    fun getToken() : String? = pref.getString(USER_TOKEN, null)

    fun resetFillInput(){
        pref.edit().remove(FILL_INPUT).apply()
    }

    fun clearSession(){
        pref.edit().remove(USER_TOKEN).apply()
        pref.edit().remove(USER_NAME).apply()
    }
}