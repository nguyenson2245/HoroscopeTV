package com.smartwavettn.horoscope.ui.utils

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.InputStreamReader

object DataJson {

    private fun readJsonFromAssets(context: Context): JsonObject {
        try {
            val inputStream = context.assets.open("json/en.json")
            val reader = InputStreamReader(inputStream)
            return JsonParser().parse(reader).asJsonObject
        } catch (e: Throwable) {
            e.printStackTrace()
            return JsonObject()
        }
    }

    fun getValueByKey(context: Context, prefix: String): String ? {
        val jsonObject = readJsonFromAssets(context)
        return jsonObject.get(prefix).asString
    }

}