package com.smartwavettn.horoscope.model

data class TibYearsPerams(
    val tibYear: Int,
    val tibYearFirstMonth: Int,
    val tibYearFirstDay: Int,
    val yearEl: Int,
    val yearAnimal: Int,
    val sogEl: Int,
    val luEl: Int,
    val vangEl: Int,
    val lungtaEl: Int,
    val laEl: Int,
    val meva: Int,
    val luMe: Int,
    val sogMe: Int,
    val vanMe: Int,
    val lunMe: Int,
    val persDays: List<Int>,
    val persStars: List<Int>,
    val persMoonDays: List<Int>

)