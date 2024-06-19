package com.smartwavettn.horoscope.ui.utils

import android.content.Context
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
        val value = jsonObject.get(prefix).toString()
        return removeQuotes(value)
    }

    private fun removeQuotes(str: String): String {
        return str.replaceFirst("\"", "").replaceFirst("\"", "")
    }



//    private fun readJsonYear(context: Context): Map<String, JsonObject> {
//        try {
//            val inputStream = context.assets.open("json/tib_years_perams.json")
//            val reader = InputStreamReader(inputStream)
//            val jsonObject = JsonParser().parse(reader).asJsonObject
//            val result = mutableMapOf<String, JsonObject>()
//            for (entry in jsonObject.entrySet()) {
//                result[entry.key] = entry.value.asJsonObject
//            }
//            return result
//        } catch (e: Throwable) {
//            e.printStackTrace()
//            return emptyMap()
//        }
//    }
//
//    fun getAllYears(context: Context) {
//        val yearMap = readJsonYear(context)
//        for ((year, yearData) in yearMap) {
//            println("Year: $year")
//            val tibYear = yearData.get("tibYear").asInt
//            val tibYearFirstMonth = yearData.get("tibYearFirstMonth").asInt
//            val tibYearFirstDay = yearData.get("tibYearFirstDay").asInt
//            val yearEl = yearData.get("yearEl").asInt
//            val yearAnimal = yearData.get("yearAnimal").asInt
//            // Sử dụng các giá trị này như cần thiết
//        }
//    }

     fun readJsonYear(context: Context): Map<String, JsonObject> {
        try {
            val inputStream = context.assets.open("json/tib_years_perams.json")
            val reader = InputStreamReader(inputStream)
            val jsonObject = JsonParser().parse(reader).asJsonObject
            val result = mutableMapOf<String, JsonObject>()
            for (entry in jsonObject.entrySet()) {
                result[entry.key] = entry.value.asJsonObject
            }
            return result
        } catch (e: Throwable) {
            e.printStackTrace()
            return emptyMap()
        }
    }

}