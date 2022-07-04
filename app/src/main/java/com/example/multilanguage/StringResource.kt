package com.example.multilanguage


object StringResource {
    var stringResource: Map<String?, Any?>? = null
    fun updateLanguage(languageType: LanguageType){
        stringResource = GlobalApplication.readFileLanguage.read(languageType)
    }

    fun getString(stringKey: String)= stringResource?.let {
       it.get(stringKey)?:"Unknown string key:${stringKey}"
    }.toString()
}