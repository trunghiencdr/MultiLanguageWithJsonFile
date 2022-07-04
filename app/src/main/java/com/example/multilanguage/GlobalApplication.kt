package com.example.multilanguage

import android.app.Application
import android.content.Context
import com.mycompany.plugins.example.common.android.AndroidSecurePref

class GlobalApplication: Application() {
    companion object{
       lateinit var readFileLanguage: ReadFileLanguage
    }
    override fun onCreate() {
        super.onCreate()
        AndroidSecurePref.initSharePreference(applicationContext)
        readFileLanguage = ReadFileLanguage.getInstance(applicationContext)
//        loadLanguageResource()
    }

    private fun loadLanguageResource() {
        val languageType = getLanguageType(AndroidSecurePref.getString("lang")?:"English")
        StringResource.updateLanguage(languageType)
    }
}