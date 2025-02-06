package com.avcialper.jdatetime.model

import com.avcialper.jdatetime.JDateTime
import java.util.Calendar
import kotlin.math.abs

data class JDate(
    /**
     * Date as a string in the format `dd.MM.yyyy`.
     */
    val date: String,
    /**
     * Day of the month. Start day `1`.
     */
    val dayOfMonth: Int,
    /**
     * Name of the day of the week.
     */
    val dayName: String,
    /**
     * Index of the day of the week. Is between 0 to 6.
     */
    val dayOfWeek: Int,
    /**
     * Month of the year. Start month `0`.
     */
    val month: Int,
    /**
     * Year field.
     */
    val year: Int
) {
    /**
     * Checks if the date is today.
     * @return `true` if the date is today, `false` otherwise.
     */
    fun isToday(): Boolean {
        return date == JDateTime.instance.date
    }

    /**
     * Checks if the date is after another date.
     * @param other The date to compare with.
     * @return `true` if the date is after the other date, `false` otherwise.
     */
    fun isAfter(other: JDate): Boolean {
        return year > other.year || (year == other.year && month > other.month) ||
                (year == other.year && month == other.month && dayOfMonth > other.dayOfMonth)
    }

    /**
     * Checks if the date is before another date.
     * @param other The date to compare with.
     * @return `true` if the date is before the other date, `false` otherwise.
     */
    fun isBefore(other: JDate): Boolean {
        return year < other.year || (year == other.year && month < other.month) ||
                (year == other.year && month == other.month && dayOfMonth < other.dayOfMonth)
    }

    /**
     * Checks if the date is before today.
     * @return `true` if the date is before today, `false` otherwise.
     */
    fun isBeforeToday(): Boolean {
        val today = JDateTime.instance
        return year < today.year || (year == today.year && month < today.month) ||
                (year == today.year && month == today.month && dayOfMonth < today.dayOfMonth)
    }

    /**
     * Checks if the date is after today.
     * @return `true` if the date is after today, `false` otherwise.
     */
    fun isAfterToday(): Boolean {
        val today = JDateTime.instance
        return year > today.year || (year == today.year && month > today.month) ||
                (year == today.year && month == today.month && dayOfMonth > today.dayOfMonth)
    }

    /**
     * Checks if the date is the same as another date.
     * @param other The date to compare with.
     * @return `true` if the date is the same as the other date, `false` otherwise.
     */
    fun isSameDay(other: JDate): Boolean {
        return this == other
    }

    /**
     * Finds the difference between two dates.
     * @param other The date to compare with.
     * @return The difference between the two dates as a [JDateDifference] object.
     */
    fun findDateDifference(other: JDate): JDateDifference {
        val isThisGreater = this.isAfter(other)

        val biggerDate = if (isThisGreater) this else other
        val smallerDate = if (isThisGreater) other else this

        var yearDiff = biggerDate.year - smallerDate.year
        var monthDiff = biggerDate.month - smallerDate.month
        var dayDiff = biggerDate.dayOfMonth - smallerDate.dayOfMonth

        if (monthDiff < 0) {
            yearDiff--
            monthDiff += 12
        }

        if (dayDiff < 0) {
            val lastMonth = Calendar.getInstance()
            lastMonth.set(Calendar.YEAR, smallerDate.year)
            lastMonth.set(Calendar.MONTH, smallerDate.month - 1)
            dayDiff += lastMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
            monthDiff--
        }

        return JDateDifference(yearDiff, monthDiff, dayDiff)
    }

    fun findDayDifference(other: JDate): Long {
        val calendar1 = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }

        val calendar2 = Calendar.getInstance().apply {
            set(other.year, other.month, other.dayOfMonth)
        }

        val diffInMillis = calendar1.timeInMillis - calendar2.timeInMillis
        val dayDiff = diffInMillis / (24 * 60 * 60 * 1000)

        return abs(dayDiff)
    }
}