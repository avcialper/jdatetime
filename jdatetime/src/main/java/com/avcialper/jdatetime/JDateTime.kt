package com.avcialper.jdatetime

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.avcialper.jdatetime.datetime.JCalendar
import com.avcialper.jdatetime.datetime.JLocalDateTime
import com.avcialper.jdatetime.model.JDate
import com.avcialper.jdatetime.model.JDateDifference
import com.avcialper.jdatetime.model.JDayOfMonth
import com.avcialper.jdatetime.util.JDay
import com.avcialper.jdatetime.util.JMonth
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

abstract class JDateTime {

    companion object {

        val instance: JDateTime
            get() = getJDateTime()

        private fun getJDateTime(): JDateTime {
            val isDeviceEqualOrHigherO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            return if (isDeviceEqualOrHigherO)
                JLocalDateTime()
            else
                JCalendar()
        }

    }

    /**
     * Current date as a string in the format `dd.MM.yyyy`.
     */
    abstract val date: String

    /**
     * Current time as a string in the format `HH:mm:ss`.
     */
    abstract val time: String

    /**
     * Current year.
     */
    abstract val year: Int

    /**
     * Current month index.
     *
     * 0 - `JANUARY`
     *
     * 11 - `DECEMBER`
     */
    abstract val month: Int

    /**
     * Current day of the month. Start day `1`.
     */
    abstract val day: Int

    /**
     * Current hour.
     */
    abstract val hour: Int

    /**
     * Current minute.
     */
    abstract val minute: Int

    /**
     * Current second.
     */
    abstract val second: Int

    /**
     * Current date as a long value representing the number of days since January 1, 1970.
     */
    abstract val epochDay: Long

    /**
     * Current time in seconds.
     */
    abstract val timeInSecond: Int

    /**
     * Current day of the week.
     *
     * 0 - `MONDAY`
     *
     * 6 - `SUNDAY`
     */
    abstract val dayOfWeek: Int

    /**
     * Current day of the month. Start day `1`.
     */
    abstract val dayOfMonth: Int

    /**
     * Current day of the year. Start day `1`.
     */
    abstract val dayOfYear: Int

    /**
     * Current day name as a [JDay].
     */
    abstract val dayName: JDay

    /**
     * Current week of the month.
     */
    abstract val weekOfMonth: Int

    /**
     * Current week of the year.
     */
    abstract val weekOfYear: Int

    /**
     * Current month as a [JMonth].
     */
    abstract val monthName: JMonth

    /**
     * Check if the current year is a leap year.
     */
    abstract val isLeapYear: Boolean

    /**
     * Get all days of the month as a list of [JDate].
     * @param year The year of the month.
     * @param month The month of the year.
     * @return A list of [JDate] objects.
     */
    abstract fun getAllDaysOfMonth(year: Int, month: Int): List<JDayOfMonth>

    /**
     * Format current date.
     *
     *  @throws UnsupportedOperationException
     * If the device is running on Android 8 (API 26) or higher, use the `format(formatter: SimpleDateFormat)` function.
     *
     * @param formatter [DateTimeFormatter]
     * @return Formatted date.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun format(formatter: DateTimeFormatter): String

    /**
     * Format current date.
     * @param formatter [SimpleDateFormat]
     * @return Formatted date.
     */
    abstract fun format(formatter: SimpleDateFormat): String

    /**
     * Format the given date.
     *
     * @throws UnsupportedOperationException
     * If the device is running on Android 8 (API 26) or higher, use the `formatDate(formatter: SimpleDateFormat, date: Calendar)` function.
     *
     * @param formatter [DateTimeFormatter]
     * @param date [LocalDate] The date to format.
     * @return Formatted date.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun formatDate(formatter: DateTimeFormatter, date: LocalDate): String

    /**
     * Format the given date.
     * @param formatter [SimpleDateFormat]
     * @param date [Calendar] The date to format.
     * @return Formatted date.
     */
    abstract fun formatDate(formatter: SimpleDateFormat, date: Calendar): String

    /**
     * Format current time.
     *
     * @throws UnsupportedOperationException
     * If the device is running on Android 8 (API 26) or higher, use the `formatTime(formatter: SimpleDateFormat)` function.
     *
     * @param formatter [DateTimeFormatter]
     * @return Formatted time.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun formatTime(formatter: DateTimeFormatter): String

    /**
     * Format current time.
     * @param formatter [SimpleDateFormat]
     * @return Formatted time.
     */
    abstract fun formatTime(formatter: SimpleDateFormat): String

    /**
     * Calculates the difference between two dates.
     *
     * @throws UnsupportedOperationException
     * If the device is running on Android 8 (API 26) or higher, use the `findDateDifference(fromDate: Calendar, toDate: Calendar)` function.
     *
     * @param fromDate [LocalDate] Start date.
     * @param toDate [LocalDate] End date.
     * @return [JDateDifference] object.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun findDateDifference(fromDate: LocalDate, toDate: LocalDate): JDateDifference

    /**
     * Calculates the difference between two dates.
     * @param fromDate [Calendar] Start date.
     * @param toDate [Calendar] End date.
     */
    abstract fun findDateDifference(fromDate: Calendar, toDate: Calendar): JDateDifference

    /**
     * Calculates the difference between two dates.
     *
     * @throws UnsupportedOperationException
     * If the device is running on Android 8 (API 26) or higher, use the `findDayDifference(fromDate: Calendar, toDate: Calendar)` function.
     *
     * @param fromDate [LocalDate] Start date.
     * @param toDate [LocalDate] End date.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun findDayDifference(fromDate: LocalDate, toDate: LocalDate): Long

    /**
     * Calculates the difference between two dates.
     * @param fromDate [Calendar] Start date.
     * @param toDate [Calendar] End date.
     */
    abstract fun findDayDifference(fromDate: Calendar, toDate: Calendar): Long

    /**
     * Calculate the length of the given month.
     */
    abstract fun lengthOfMonth(year: Int, month: Int): Int

    /**
     * Check if the given year is a leap year.
     */
    fun isLeapYear(year: Int): Boolean =
        (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
}