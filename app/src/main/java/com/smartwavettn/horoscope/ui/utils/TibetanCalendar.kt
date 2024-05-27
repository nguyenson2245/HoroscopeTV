import java.util.Calendar

data class TibetanCalendar(
    val year: Int,
    val month: Int,
    val day: Int,
    val isLeapMonth: Boolean,
    val isLeapDay: Boolean
) {

    companion object {
        const val RABJUNG_BEGINNING = 1027
        const val RABJUNG_CYCLE_LENGTH = 60
        const val YEAR_DIFF = 125
        const val JULIAN_TO_UNIX = 2440587.5
        const val MS_IN_YEAR = 86400000
        const val MIN_IN_DAY = 1440
        const val YEAR0 = 806
        const val MONTH0 = 3
        const val BETA_STAR = 61
        const val BETA = 123
        const val M0 = 2015501 + 4783 / 5656
        const val M1 = 167025 / 5656
        const val M2 = M1 / 30
        const val S0 = 743 / 804
        const val S1 = 65 / 804
        const val S2 = S1 / 30
        const val A0 = 475 / 3528
        const val A1 = 253 / 3528
        const val A2 = 1 / 28
        val MOON_TAB = listOf(0, 5, 10, 15, 19, 22, 24, 25)
        val SUN_TAB = listOf(0, 6, 10, 11)
        val YEAR_ELEMENTS = listOf("Wood", "Fire", "Earth", "Iron", "Water")
        val YEAR_ANIMALS = listOf(
            "Mouse", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse",
            "Sheep", "Monkey", "Bird", "Dog", "Pig"
        )
        val YEAR_GENDER = listOf("Male", "Female")

        fun getTibetanDate(westernDate: Calendar): TibetanCalendar {
            val tibetanYear = westernDate.get(Calendar.YEAR) + YEAR_DIFF
            val jd = julianFromUnix(westernDate)
            val monthCounts = mutableListOf<Int>()
            for (i in 0 until 2) {
                monthCounts.add(monthCountFromTibetan(tibetanYear + if (i == 0) -1 else 1, 1, true))
            }
            val trueDate = monthCounts.map { 1 + 30 * it }
            val jds = trueDate.map { julianFromTrueDate(it.toDouble()) }
            var trueDate0 = trueDate[0]
            var trueDate1 = trueDate[1]
            var jd0 = jds[0]
            var jd1 = jds[1]

            while (trueDate0 < trueDate1 - 1 && jd0 < jd1) {
                val nTrueDate = ((trueDate0 + trueDate1) / 2).toInt()
                val njd = julianFromTrueDate(nTrueDate.toDouble())
                if (njd < jd) {
                    trueDate0 = nTrueDate
                    jd0 = njd
                } else {
                    trueDate1 = nTrueDate
                    jd1 = njd
                }
            }

            val winnerJd = if (jd0 == jd) jd0 else jd1
            val winnerTrueDate = if (jd0 == jd) trueDate0 else trueDate1

            val isLeapDay = winnerJd > jd
            val monthCount = ((winnerTrueDate - 1) / 30).toInt()
            val day = if (winnerTrueDate % 30 == 0) 30 else (winnerTrueDate % 30).toInt()

            val (year, month, isLeapMonth) = getMonthFromMonthCount(monthCount)
            return getDayFromTibetan(year, month, isLeapMonth, day, isLeapDay)
        }

        fun julianFromUnix(unixDate: Calendar): Double {
            val date = Calendar.getInstance().apply {
                set(unixDate.get(Calendar.YEAR), unixDate.get(Calendar.MONTH), unixDate.get(Calendar.DAY_OF_MONTH))
                set(Calendar.HOUR_OF_DAY, 18)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val unixTime = ((date.timeInMillis / 86400000) + 2440587.5).toInt()
            return unixTime.toDouble()
        }

        fun monthCountFromTibetan(year: Int, month: Int, isLeapMonth: Boolean): Int {
            val wYear = year - YEAR_DIFF
            val solarMonth = 12 * (wYear - YEAR0) + month - MONTH0
            val hasLeap = isDoubledMonth(year, month)
            val isLeap = if (hasLeap && isLeapMonth) 1 else 0
            return ((67 * solarMonth + BETA_STAR + 17) / 65) - isLeap
        }

        fun isDoubledMonth(tYear: Int, month: Int): Boolean {
            val mp = 12 * (tYear - YEAR_DIFF - YEAR0) + month
            return (2 * mp) % 65 == BETA % 65 || (2 * mp) % 65 == (BETA + 1) % 65
        }

        fun julianFromTrueDate(dayCount: Double): Double {
            val monthCount = (dayCount - 1) / 30
            return trueDateFromMonthCountDay(dayCount.toInt(), monthCount).toInt().toDouble()
        }

        fun trueDateFromMonthCountDay(day: Int, monthCount: Double): Double {
            return (meanDate(day.toDouble(), monthCount) + moonEqu(day.toDouble(), monthCount) / 60 - sunEqu(day.toDouble(), monthCount) / 60)
        }

        fun meanDate(day: Double, monthCount: Double): Double {
            return monthCount * M1 + day * M2 + M0
        }

        fun moonEqu(day: Double, monthCount: Double): Double {
            return moonTab(28 * moonAnomaly(day, monthCount))
        }

        fun sunEqu(day: Double, monthCount: Double): Double {
            return sunTab(12 * sunAnomaly(day, monthCount))
        }

        fun moonTab(i: Double): Double {
            val a = moonTabInt(i.toInt())
            val x = frac(i)
            return if (x != 0.0) {
                val b = moonTabInt(i.toInt() + 1)
                a + (b - a) * x
            } else a.toDouble()
        }

        fun moonTabInt(i: Int): Int {
            val iMod = i % 28
            return when {
                iMod <= 7 -> MOON_TAB[iMod]
                iMod <= 14 -> MOON_TAB[14 - iMod]
                iMod <= 21 -> -MOON_TAB[iMod - 14]
                else ->
                    -MOON_TAB[28 - iMod]
            }
        }

        fun moonAnomaly(day: Double, monthCount: Double): Double {
            return monthCount * A1 + day * A2 + A0
        }

        fun sunAnomaly(day: Double, monthCount: Double): Double {
            return meanSun(day, monthCount) - 1 / 4
        }

        fun meanSun(day: Double, monthCount: Double): Double {
            return monthCount * S1 + day * S2 + S0
        }

        fun sunTab(i: Double): Double {
            val a = sunTabInt(i.toInt())
            val x = frac(i)
            return if (x != 0.0) {
                val b = sunTabInt(i.toInt() + 1)
                a + (b - a) * x
            } else a.toDouble()
        }

        fun sunTabInt(i: Int): Int {
            val iMod = i % 12
            return when {
                iMod <= 3 -> SUN_TAB[iMod]
                iMod <= 6 -> SUN_TAB[6 - iMod]
                iMod <= 9 -> -SUN_TAB[iMod - 6]
                else -> -SUN_TAB[12 - iMod]
            }
        }

        fun frac(a: Double): Double {
            return a % 1
        }

        fun getMonthFromMonthCount(monthCount: Int): Triple<Int, Int, Boolean> {
            val x = ((65 * monthCount + BETA) / 67).toInt()
            val tMonth = amod(x, 12)
            val tYear = (x / 12) + 1 + YEAR0 + YEAR_DIFF
            val isLeapMonth = ((65 * (monthCount + 1) + BETA) / 67).toInt() == x
            return Triple(tYear, tMonth, isLeapMonth)
        }

        fun amod(a: Int, b: Int): Int {
            return if (a % b == 0) b else a % b
        }

        fun getDayFromTibetan(year: Int, month: Int, isLeapMonth: Boolean, day: Int, isLeapDay: Boolean): TibetanCalendar {
            val julianDate = julianFromTibetan(year, month, isLeapMonth, day)
            val monthCount = monthCountFromTibetan(year, month, isLeapMonth)
            val dayBefore = getDayBefore(day, monthCount)
            val julianDatePrevious = trueDateFromMonthCountDay(dayBefore["day"]!!, dayBefore["monthCount"]!!.toDouble())
            val isLeapDayChecked = isLeapDay && julianDate == julianDatePrevious + 2
            return TibetanCalendar(year, month, day, isLeapMonth, isLeapDayChecked)
        }

        fun julianFromTibetan(year: Int, month: Int, isLeapMonth: Boolean, day: Int): Double {
            val monthCount = monthCountFromTibetan(year, month, isLeapMonth)
            return trueDateFromMonthCountDay(day, monthCount.toDouble())
        }

        fun getDayBefore(day: Int, monthCount: Int): Map<String, Int> {
            return if (day == 1) mapOf("day" to 30, "monthCount" to monthCount - 1)
            else mapOf("day" to day - 1, "monthCount" to monthCount)
        }

        fun getYearAttributes(tibetanYear: Int): YearAttribute {
            val animal = YEAR_ANIMALS[(tibetanYear + 1) % 12]
            val element = YEAR_ELEMENTS[(tibetanYear - 1) / 2 % 5]
            val gender = YEAR_GENDER[(tibetanYear + 1) % 2]
            return YearAttribute(animal, element, gender)
        }

        fun getAnimalAndElementByYear(tibetanYear: Int, westernBirthYear: Int): YearAttribute {
            val yearOffset = Calendar.getInstance().get(Calendar.YEAR) - westernBirthYear
            val birthTibetanYear = tibetanYear - yearOffset
            return getYearAttributes(birthTibetanYear)
        }
    }

    data class YearAttribute(val animal: String, val element: String, val gender: String)
}
