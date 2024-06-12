package com.smartwavettn.horoscope.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smartwavettn.horoscope.local.dao.Dao
import com.smartwavettn.horoscope.model.PersonalInformation
import com.smartwavettn.horoscope.ui.utils.KeyWord


@Database(entities = [PersonalInformation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getInformationDao(): Dao

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, KeyWord.database
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}