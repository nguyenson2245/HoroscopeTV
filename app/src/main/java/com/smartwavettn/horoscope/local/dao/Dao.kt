package com.smartwavettn.horoscope.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.smartwavettn.horoscope.model.PersonalInformation


@Dao
interface Dao {

    @Query("SELECT * FROM PersonalInformation WHERE isProfile = 1 LIMIT 1")
    fun getProfileInformation(): PersonalInformation

    @Query("SELECT * FROM PersonalInformation")
    fun getAll(): List<PersonalInformation>

    @Insert
    suspend fun insertAll(vararg information: PersonalInformation)

    @Query("SELECT * FROM PersonalInformation where name= :title")
    fun checkName(title: String): List<PersonalInformation>
    @Query("SELECT * FROM PersonalInformation where isProfile= :isProfile  LIMIT 1")
    fun getProfile(isProfile: Boolean):LiveData<PersonalInformation>

    @Query("SELECT * FROM PersonalInformation")
    fun getListNote(): LiveData<List<PersonalInformation>>

    @Delete
    suspend fun delete(vararg information: PersonalInformation)

    @Update
    fun updatePersonal(vararg information: PersonalInformation)

    @Query("SELECT * FROM PersonalInformation")
    fun getLiveDataPersonalInformation(): LiveData<List<PersonalInformation>>


}