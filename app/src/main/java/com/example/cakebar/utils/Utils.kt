package com.example.cakebar.utils

import android.content.Context
import com.example.cakebar.MyApplication
import io.realm.Realm

object Utils {

    fun openRealm(context: Context): Realm {
        return try {
            Realm.getDefaultInstance()
        } catch (e: IllegalStateException) {
            MyApplication.initRealm(context)
            Realm.getDefaultInstance()
        }
    }
}