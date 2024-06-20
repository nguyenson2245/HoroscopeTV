package com.smartwavettn.horoscope.customview.model

data class Year(
    val tibYear: Int = 0,
    val tibYearFirstMonth: Int = 0,
    val tibYearFirstDay: Int = 0,
    val yearEl: Int = 0,
    val yearAnimal: Int = 0,
    val sogEl: Int = 0,
    val luEl: Int = 0,
    val vangEl: Int = 0,
    val lungtaEl: Int = 0,
    val laEl: Int = 0,
    val meva: Int = 0,
    val luMe: Int = 0,
    val sogMe: Int = 0,
    val vanMe: Int = 0,
    val lunMe: Int = 0,
    val persDays: List<Int> = arrayListOf(),
    val persStars: List<Int> = arrayListOf(),
    val persMoonDays: List<Int> = arrayListOf()
)