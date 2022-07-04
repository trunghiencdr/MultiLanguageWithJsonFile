package com.mycompany.plugins.example.common.android

interface AndroidStorage {
    fun putString(key: String, value: String)

    fun getString(key: String): String?

    fun delete(key: String)

    fun clear()
}
