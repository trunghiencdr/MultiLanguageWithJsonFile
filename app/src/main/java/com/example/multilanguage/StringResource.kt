package com.example.multilanguage


object StringResource {
    var stringResource: Map<String?, Any?>? = null
    fun updateLanguage(languageType: LanguageType){
        stringResource = GlobalApplication.readFileLanguage.read(languageType)
    }
}