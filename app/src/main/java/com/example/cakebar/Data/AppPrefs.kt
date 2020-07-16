package com.example.cakebar.Data
import android.content.Context
import android.content.SharedPreferences

class AppPrefs(context: Context) {

    companion object {
        private const val PREFS_FILE = "default_app_prefs"

        //driver and co-driver
        private const val KEY_CUSTOMER_NAME = "key_customer_name"
        private const val KEY_TABLE = "key_table"

        private const val KEY_CAKES_FLAG = "key_cakes_flag"
        private const val KEY_DESERTS_FLAG = "key_deserts_flag"
        private const val KEY_COFFEE_FLAG = "key_coffee_flag"
        private const val KEY_DRINKS_FLAG = "key_drinks_flag"

        //this method is called after driver account changes made from web
        fun saveDriverSettings(context: Context) {
            val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString(KEY_CUSTOMER_NAME, "string")
            editor.putString(KEY_TABLE, "string")
            editor.apply()
        }

        fun resetUser(context: Context) {
            val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
            val editor = prefs.edit()

            editor.clear()
            editor.putString(KEY_CUSTOMER_NAME, "string")
            editor.putString(KEY_TABLE, "string")
            editor.apply()
        }

    }

    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
    }

    var cakesFlag : Boolean
    get() = prefs.getBoolean(KEY_CAKES_FLAG, false)
        set(cakesFlag) = setBooleanPref(KEY_CUSTOMER_NAME, cakesFlag)

    var dessertsFlag : Boolean
        get() = prefs.getBoolean(KEY_DESERTS_FLAG, false)
        set(dessertsFlag) = setBooleanPref(KEY_DESERTS_FLAG, dessertsFlag)

    var coffeeFlag : Boolean
        get() = prefs.getBoolean(KEY_COFFEE_FLAG, false)
        set(coffeeFlag) = setBooleanPref(KEY_COFFEE_FLAG, coffeeFlag)

    var drinksFlag : Boolean
        get() = prefs.getBoolean(KEY_DRINKS_FLAG, false)
        set(drinksFlag) = setBooleanPref(KEY_DRINKS_FLAG, drinksFlag)

    var customerName: String
        get() = prefs.getString(KEY_CUSTOMER_NAME, "") ?: ""
        set(customerName) = setStringPref(KEY_CUSTOMER_NAME, customerName)

    var tableName: String
        get() = prefs.getString(KEY_TABLE, "") ?: ""
        set(tableName) = setStringPref(KEY_TABLE, tableName)

    /**
     * Private helper setter
     */
    private fun setStringPref(key: String, value: String?) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun setLongPref(key: String, value: Long) {
        val editor = prefs.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    private fun setBooleanPref(key: String, value: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun setIntPref(key: String, value: Int) {
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun setFloatPref(key: String, value: Float) {
        val editor = prefs.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    private fun setStringSetPref(key: String, value: Set<String>?) {
        val editor = prefs.edit()
        editor.putStringSet(key, value)
        editor.apply()
    }
}