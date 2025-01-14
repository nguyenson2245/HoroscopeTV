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

    fun getListLiveData(): LiveData<List<PersonalInformation>> =
        appDatabase.getInformationDao().getListNote()

    fun getListProfiles(): List<PersonalInformation> = appDatabase.getInformationDao().getAll()
    fun getProFile():LiveData<PersonalInformation> = appDatabase.getInformationDao().getProfile(true)
    fun checkProFile():Boolean = appDatabase.getInformationDao().checkProfile(true) != null
    suspend fun deletePersonal(personalInformation: PersonalInformation) =
        appDatabase.getInformationDao().delete(personalInformation)
    fun getProFileSelect():LiveData<PersonalInformation> = appDatabase.getInformationDao().getProfileSelect(true)

    fun getProfileInformation(personalInformation: PersonalInformation) =    appDatabase.getInformationDao().getProfileInformation()
    fun updateProfile(personalInformation: PersonalInformation)  = appDatabase.getInformationDao().updatePersonal(personalInformation)
}
