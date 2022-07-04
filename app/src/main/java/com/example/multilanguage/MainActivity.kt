package com.example.multilanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mycompany.plugins.example.common.android.AndroidSecurePref
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpSpinner()
        setEvent()
    }

    private fun setEvent() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val languageType = getLanguageType(spinner.selectedItem.toString())
                updateLanguage(languageType)
                changeLanguageOfCurrentScreen()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // set event for navigate to new activity

        button.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    private fun updateLanguage(languageType: LanguageType){
        StringResource.updateLanguage(languageType)
        AndroidSecurePref.putString("lang", languageType.name)
    }

    private fun changeLanguageOfCurrentScreen() {
        textView.text = StringResource.getString("complete_account_button_complete")
    }

    private fun setUpSpinner() {
        val languages = enumNameToArray<LanguageType>()
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter
        }

        spinner.setSelection(languages.indexOf(AndroidSecurePref.getString("lang")?:"English"))
    }

}

