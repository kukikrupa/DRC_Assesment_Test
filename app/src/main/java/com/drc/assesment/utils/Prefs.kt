package com.drc.assesment.utils

import android.content.Context
import android.content.SharedPreferences
import com.drc.assesment.model.User
import com.google.gson.Gson

class Prefs(context: Context) {

    private val PREF_USER_DATA = "userData"

    private val SP_NAME = Prefs::class.java.name
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    var isLoggedIn: Boolean
        set(value) = sharedPreferences!!.edit().putBoolean("key", value).apply()
        get() = sharedPreferences!!.getBoolean("key", false)

    var userDataModel: User?
        get() = Gson().fromJson<User>(sharedPreferences!!.getString(PREF_USER_DATA, ""), User::class.java)
        set(userDataModel) = sharedPreferences!!.edit().putString(PREF_USER_DATA, Gson().toJson(userDataModel)).apply()


    fun clearPrefs() {
        sharedPreferences!!.edit().clear().apply()
    }

    companion object {
        var prefs: Prefs? = null

        fun getInstance(context: Context): Prefs? {
            prefs = if (prefs != null) prefs else Prefs(context)
            return prefs
        }
    }
}