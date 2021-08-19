package com.example.firstdynamicmodule

import android.text.TextUtils

object ModuleConstant
{


    val SecondActivity: String = "com.example.firstdynamicmodule.SecondActivity"

    val DynamicModuleActivity: String = "com.example.firstdynamicmodule.LoginActivity"


    private fun capitalize(str: String): String {
        if (TextUtils.isEmpty(str)) {
            return str
        }
        val arr = str.toCharArray()
        var capitalizeNext = true
        var phrase: String? = ""
        for (c in arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c)
                capitalizeNext = false
                continue
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true
            }
            phrase += c
        }
        return phrase.toString()
    }
}