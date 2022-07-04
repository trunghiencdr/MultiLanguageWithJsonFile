package com.example.multilanguage

import android.content.Context
import android.util.Log
import org.json.JSONObject
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class ReadFileLanguage private constructor(var context: Context){
    init {

    }
    companion object: SingletonHolder<ReadFileLanguage, Context>(::ReadFileLanguage)
    @OptIn(ExperimentalTime::class)
    fun read(type: LanguageType): Map<String?, Any?>? {
        val fileName = getFileLanguage(type)
        val fileInString: String =
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        Log.d("HIEN", fileInString)

        // add to HashMap
        val jsonObject = JSONObject(fileInString)
        val duration = measureTime {
            jsonToMap(jsonObject)
        }
        Log.d("HIEN", "measure time fun jsonToMap: ${duration.inMilliseconds} + capacity of map: ")
        return jsonToMap(jsonObject)

    }

    fun getFileLanguage(type: LanguageType): String =
        when(type){
            LanguageType.English -> "translation_en.json"
            LanguageType.Franch -> "translation_fr.json"
            else -> "translation_en.json"
        }

}