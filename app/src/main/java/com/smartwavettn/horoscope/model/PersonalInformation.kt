package com.smartwavettn.horoscope.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class PersonalInformation(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String = "",
    val date: String = "",
    val icon: Int = 0,
    val iconImage: String = "",
    val isProfile: Boolean
)