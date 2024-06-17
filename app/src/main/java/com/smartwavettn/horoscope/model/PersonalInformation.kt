package com.smartwavettn.horoscope.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class PersonalInformation(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String = "",
    var date: String = "",
    var icon: Int = 0,
    var iconImage: String = "",
    var isProfile: Boolean,
    var isSelect : Boolean = false
): Serializable