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

    fun getMessageBigComboKeys(context: Context) {
        val jsonObject = readJsonFromAssets(context)
        val keys = mutableListOf<String>()
        for (key in jsonObject.keySet()) {
            if (key.startsWith("Message-Big-Combo-")) {
                keys.add(key)
                val value = jsonObject.get(key).asString
                Log.d("JsonDataFragment_sdsndkdsk", "$key: $value")
            }
        }
    }

    //  lấy gt key cụ thể
    fun getValueForKey(context: Context, key: String): String {
        val jsonObject = readJsonFromAssets(context)
        return jsonObject.get(key).asString
    }
}