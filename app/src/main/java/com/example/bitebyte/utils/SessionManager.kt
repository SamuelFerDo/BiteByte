package com.example.bitebyte.utils
import android.content.Context
import retrofit2.http.GET

class SessionManager(context: Context) {
    private var pref = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    companion object {
        const val USER_NAME = "user_name"
        const val USER_TOKEN = "user_token"
        const val USER_EMAIL = "user_email"
        const val FILL_INPUT = "fill_input"
        const val USER_ID = "user_id"
        const val USER_PHOTO = "user_photo"
        const val USER_AGE = "user_age"
        const val USER_GENDER = "user_gender"
        const val USER_HEIGHT = "user_height"
        const val USER_WEIGHT = "user_weight"
        const val USER_HEALTH_CONCERN = "user_health_concern"
        const val USER_MENU_TYPE = "user_menu_type"
        const val USER_ACTIVITY_TYPE = "user_activity_type"
    }

    fun addUserSession(name: String, token: String, email: String, id:String){
        pref.edit().putString(USER_NAME, name).apply()
        pref.edit().putString(USER_TOKEN, token).apply()
        pref.edit().putString(USER_EMAIL, email).apply()
        pref.edit().putString(USER_ID, id).apply()
    }

    fun addUserInput(age:String, gender:String, height:String, weight:String, health_concern:String, menu_type:String, activity_type:String){
    pref.edit().putString(USER_AGE, age).apply()
    pref.edit().putString(USER_GENDER, gender).apply()
    pref.edit().putString(USER_HEIGHT, height).apply()
    pref.edit().putString(USER_WEIGHT, weight).apply()
    pref.edit().putString(USER_HEALTH_CONCERN, health_concern).apply()
    pref.edit().putString(USER_MENU_TYPE, menu_type).apply()
    pref.edit().putString(USER_ACTIVITY_TYPE, activity_type).apply()
    }

    fun updateName(name: String){
        pref.edit().putString(USER_NAME, name).apply()
    }

    fun addFillInput(fillInput: String){
        pref.edit().putString(FILL_INPUT, fillInput).apply()
    }

    fun changePhoto(userPhoto: String){
        pref.edit().putString(USER_PHOTO, userPhoto).apply()
    }

    fun getUserPhoto() : String? = pref.getString(USER_PHOTO, null)

    fun getFillInput() : String? = pref.getString(FILL_INPUT, null)

    fun getName() : String? = pref.getString(USER_NAME, null)

    fun getID() : String? = pref.getString(USER_ID, null)

    fun getToken() : String? = pref.getString(USER_TOKEN, null)
    fun getEmail() : String? = pref.getString(USER_EMAIL, null)
    fun getAge() : String? = pref.getString(USER_AGE, null)
    fun getGender() : String? = pref.getString(USER_GENDER, null)
    fun getHeight() : String? = pref.getString(USER_HEIGHT, null)
    fun getWeight() : String? = pref.getString(USER_WEIGHT, null)
    fun getHealthConcern() : String? = pref.getString(USER_HEALTH_CONCERN, null)
    fun getMenuType() : String? = pref.getString(USER_MENU_TYPE, null)
    fun getActivityType() : String? = pref.getString(USER_ACTIVITY_TYPE, null)

    fun resetFillInput(){
        pref.edit().remove(FILL_INPUT).apply()
    }

    fun clearSession(){
        pref.edit().remove(USER_TOKEN).apply()
        pref.edit().remove(USER_NAME).apply()
        pref.edit().remove(USER_EMAIL).apply()
        pref.edit().remove(USER_ID).apply()
        pref.edit().remove(USER_PHOTO).apply()
        pref.edit().remove(USER_AGE).apply()
        pref.edit().remove(USER_GENDER).apply()
        pref.edit().remove(USER_HEIGHT).apply()
        pref.edit().remove(USER_WEIGHT).apply()
        pref.edit().remove(USER_HEALTH_CONCERN).apply()
        pref.edit().remove(USER_MENU_TYPE).apply()
        pref.edit().remove(USER_ACTIVITY_TYPE).apply()
        pref.edit().remove(FILL_INPUT).apply()
    }
}