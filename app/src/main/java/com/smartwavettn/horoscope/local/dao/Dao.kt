package com.smartwavettn.horoscope.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.smartwavettn.horoscope.model.PersonalInformation


@Dao
interface Dao {
    @Query("SELECT * FROM PersonalInformation")
    fun getAll(): List<PersonalInformation>

    @Insert
  suspend  fun insertAll(vararg information: PersonalInformation)

    @Query("SELECT * FROM PersonalInformation where name= :title")
      fun checkName(title: String): List<PersonalInformation>

    @Delete
    suspend fun delete(vararg information: PersonalInformation)

    @Query("SELECT * FROM PersonalInformation")
    fun getLiveDataPersonalInformation(): LiveData<List<PersonalInformation>>


}