package com.mycompany.plugins.example.common.android

import android.content.Context
import android.content.SharedPreferences
import com.example.multilanguage.Const

object AndroidSecurePref: AndroidStorage {

    private lateinit var encryptedSharedPreferences: SharedPreferences

    fun initSharePreference(context: Context) {
//            encryptedSharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                EncryptedSharedPreferences.create(
//                    context,
//                    Const.STORAGE_FILENAME,
//                    MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
//                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//                )
//            } else {
//            context.getSharedPreferences(Const.STORAGE_FILENAME, Context.MODE_PRIVATE)
//            }
        encryptedSharedPreferences =
            context.getSharedPreferences(Const.STORAGE_FILENAME, Context.MODE_PRIVATE)
    }


    override fun putString(key: String, value: String) {
        encryptedSharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    override fun getString(key: String): String? =
        encryptedSharedPreferences.getString(key, null)

    override fun delete(key: String) {
        encryptedSharedPreferences.edit()
            .remove(key)
            .apply()
    }

    override fun clear() {
        encryptedSharedPreferences.edit()
            .clear()
            .apply()
    }

    fun registerListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        encryptedSharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unRegisterListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        encryptedSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}
