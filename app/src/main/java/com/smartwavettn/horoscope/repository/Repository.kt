package com.smartwavettn.horoscope.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.smartwavettn.horoscope.local.AppDatabase

import com.smartwavettn.horoscope.model.PersonalInformation

class Repository(context: Context) {
    private val appDatabase: AppDatabase = AppDatabase.getInstance(context)

    suspend fun addPersonalInformation(personalInformation: PersonalInformation) =
        appDatabase.getInformationDao().insertAll(personalInformation)

     fun updatePersonalInformation(personalInformation: PersonalInformation) =
        appDatabase.getInformationDao().updatePersonal(personalInformation)


    fun getListLiveData(): LiveData<List<PersonalInformation>> = appDatabase.getInformationDao().getListNote()

     suspend fun deletePersonal(personalInformation: PersonalInformation) =appDatabase.getInformationDao().delete(personalInformation)
}
