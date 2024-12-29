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
     * Gets the current date as a string in the format `dd.MM.yyyy`.
     */
    abstract val date: String

    /**
     * Gets the current time as a string in the format `HH:mm:ss`.
     */
    abstract val time: String

    /**
     * Gets the current year field.
     */
    abstract val year: Int

    /**
     * Gets the current month-of-year field.
     *
     * 0 - `JANUARY`
     *
     * 11 - `DECEMBER`
     */
    abstract val month: Int

    /**
     * Gets the current day-of-month field. Start day `1`.
     */
    abstract val day: Int

    /**
     * Gets the current hour-of-day field.
     */
    abstract val hour: Int

    /**
     * Gets the current minute-of-hour field.
     */
    abstract val minute: Int

    /**
     * Gets the current second-of-minute field.
     */
    abstract val second: Int

    /**
     * Gets the current date as a long value representing the number of days since January 1, 1970.
     */
    abstract val epochDay: Long

    /**
     * Gets the time as seconds of day, from 0 to 24 * 60 * 60 - 1.
     */
    abstract val timeInSecond: Int

    /**
     * Gets the current day-of-week field.
     *
     * 0 - `MONDAY`
     *
     * 6 - `SUNDAY`
     */
    abstract val dayOfWeek: Int

    /**
     * Gets the current day-of-month field. Start day `1`.
     */
    abstract val dayOfMonth: Int

    /**
     * Gets the current day-of-year field. Start day `1`.
     */
    abstract val dayOfYear: Int

    /**
     * Gets the current day name field, which is an enum [JDay].
     */
    abstract val dayName: JDay

    /**
     * Gets the current week-of-month field.
     */
    abstract val weekOfMonth: Int

    /**
     * Gets the current week-of-year field.
     */
    abstract val weekOfYear: Int

    /**
     * Gets the current month name field, which is an enum [JMonth].
     */
    abstract val monthName: JMonth

    /**
     * Check if the current year is a leap year.
     */
    abstract val isLeapYear: Boolean

    /**
     * Gets all days of the month-of-year as a list of [JDate].
     * @param year Desired year.
     * @param month The month-of-year. (0 - 11)
     * @return A list of [JDate] objects.
     */
    abstract fun getAllDaysOfMonth(year: Int, month: Int): List<JDayOfMonth>

    /**
     * Formats current date using a formatter.
     *
     *  @throws UnsupportedOperationException
     * If the device is running below Android 8 (API 26), use the `format(formatter: SimpleDateFormat)` function.
     *
     * @param formatter [DateTimeFormatter]
     * @return Formatted date.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun format(formatter: DateTimeFormatter): String

    /**
     * Formats current date using a formatter.
     * @param formatter [SimpleDateFormat]
     * @return Formatted date.
     */
    abstract fun format(formatter: SimpleDateFormat): String

    /**
     * Formats the given date using a formatter.
     *
     * @throws UnsupportedOperationException
     * If the device is running below Android 8 (API 26), use the `formatDate(formatter: SimpleDateFormat, date: Calendar)` function.
     *
     * @param formatter [DateTimeFormatter]
     * @param date [LocalDate] The date to format.
     * @return Formatted date.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun formatDate(formatter: DateTimeFormatter, date: LocalDate): String

    /**
     * Formats the given date using a formatter.
     * @param formatter [SimpleDateFormat]
     * @param date [Calendar] The date to format.
     * @return Formatted date.
     */
    abstract fun formatDate(formatter: SimpleDateFormat, date: Calendar): String

    /**
     * Formats current time using a formatter.
     *
     * @throws UnsupportedOperationException
     * If the device is running below Android 8 (API 26), use the `formatTime(formatter: SimpleDateFormat)` function.
     *
     * @param formatter [DateTimeFormatter]
     * @return Formatted time.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun formatTime(formatter: DateTimeFormatter): String

    /**
     * Formats current time using a formatter.
     * @param formatter [SimpleDateFormat]
     * @return Formatted time.
     */
    abstract fun formatTime(formatter: SimpleDateFormat): String

    /**
     * Calculates the difference between two dates.
     *
     * @throws UnsupportedOperationException
     * If the device is running below Android 8 (API 26), use the `findDateDifference(fromDate: Calendar, toDate: Calendar)` function.
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
     * If the device is running below Android 8 (API 26), use the `findDayDifference(fromDate: Calendar, toDate: Calendar)` function.
     *
     * @param fromDate [LocalDate] Start date.
     * @param toDate [LocalDate] End date.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun findDayDifference(fromDate: LocalDate, toDate: LocalDate): Long

    /**
     * Calculates the day difference between two dates.
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