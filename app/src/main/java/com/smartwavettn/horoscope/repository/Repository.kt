package com.smartwavettn.horoscope.repository

import android.content.Context
import com.smartwavettn.horoscope.local.AppDatabase

import com.smartwavettn.horoscope.model.PersonalInformation

class Repository(context: Context) {
    private val appDatabase : AppDatabase = AppDatabase.getInstance(context)

   suspend fun addPersonalInformation(history: PersonalInformation) =
        appDatabase.getInformationDao().insertAll(history)

}