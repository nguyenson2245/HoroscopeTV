package com.smartwavettn.horoscope.ui.utils

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.InputStreamReader
import kotlin.math.log

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

    fun getValueByKey(context: Context, prefix: String) {
        val jsonObject = readJsonFromAssets(context)
        val keys = mutableListOf<String>()
        Log.d("logDataJson", " \n$prefix")
        for (key in jsonObject.keySet()) {
            if (key.startsWith(prefix)) {
                keys.add(key)
                val value = jsonObject.get(key).asString
                Log.d("logDataJson", "$key: $value")
            }
        }
    }

    fun getMessagePersonalDayKeys(context: Context, prefix: String): Map<String, String> {
        val jsonObject = readJsonFromAssets(context)
        val keys = mutableMapOf<String, String>()
        Log.d("logDataJson", " \n$prefix")
        for (key in jsonObject.keySet()) {
            if (key.startsWith(prefix)) {
                keys[key] = jsonObject.get(key).asString
            }
        }
        return keys
    }

}