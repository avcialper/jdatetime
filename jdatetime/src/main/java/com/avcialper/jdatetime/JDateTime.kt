package com.avcialper.jdatetime

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.avcialper.jdatetime.datetime.JCalendar
import com.avcialper.jdatetime.datetime.JLocalDateTime
import com.avcialper.jdatetime.model.JDate
import com.avcialper.jdatetime.model.JDateDifference
import com.avcialper.jdatetime.util.JDay
import com.avcialper.jdatetime.util.JMonth
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

abstract class JDateTime {

    companion object {

        // Determines if the device's API level is Android 8 (API 26) or higher
        val isDeviceEqualOrHigherO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

        // Provides an instance of JDateTime based on the device's API level
        val instance: JDateTime
            get() = getJDateTime()

        private fun getJDateTime(): JDateTime {
            // Returns JLocalDateTime for API 26+, otherwise JCalendar
            return if (isDeviceEqualOrHigherO)
                JLocalDateTime()
            else
                JCalendar()
        }

    }

    /** Current date as a string in the format `dd.MM.yyyy` */
    abstract val date: String

    /** Current time as a string in the format `HH:mm:ss` */
    abstract val time: String

    /** Current year */
    abstract val year: Int

    /** Current month (0 for January to 11 for December) */
    abstract val month: Int

    /** Current day of the month, starting from `1` */
    abstract val dayOfMonth: Int

    /** Current hour of the day */
    abstract val hour: Int

    /** Current minute */
    abstract val minute: Int

    /** Current second */
    abstract val second: Int

    /** Number of days since `January 1, 1970` */
    abstract val epochDay: Long

    /** Time of day in seconds, from `0` to `24 * 60 * 60 - 1` */
    abstract val timeInSecond: Int

    /** Current day of the week (0 for Monday to 6 for Sunday) */
    abstract val dayOfWeek: Int

    /** Current day of the year */
    abstract val dayOfYear: Int

    /** Day name as [JDay] enum */
    abstract val dayName: JDay

    /** Week of the month */
    abstract val weekOfMonth: Int

    /** Week of the year */
    abstract val weekOfYear: Int

    /** Month name as [JMonth] enum */
    abstract val monthName: JMonth

    /** True if the current year is a leap year */
    abstract val isLeapYear: Boolean

    /** Current date as a [JDate] object. */
    abstract val jDate: JDate

    /**
     * Returns all days of the given month as a list of [JDate].
     * @param year Desired year
     * @param month Month of the year (0 for January to 11 for December)
     */
    abstract fun getAllDaysOfMonth(year: Int, month: Int): List<JDate>

    /**
     * Formats the current date using a [DateTimeFormatter].
     * Throws an exception for API levels below 26.
     * @param formatter DateTimeFormatter instance
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun format(formatter: DateTimeFormatter): String

    /**
     * Formats the current date using a [SimpleDateFormat].
     * @param formatter SimpleDateFormat instance
     */
    abstract fun format(formatter: SimpleDateFormat): String

    /**
     * Formats a given date using a [DateTimeFormatter].
     * Throws an exception for API levels below 26.
     * @param formatter DateTimeFormatter instance
     * @param date LocalDate instance to format
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun formatDate(formatter: DateTimeFormatter, date: LocalDate): String

    /**
     * Formats a given date using a [SimpleDateFormat].
     * @param formatter SimpleDateFormat instance
     * @param date Calendar instance to format
     */
    abstract fun formatDate(formatter: SimpleDateFormat, date: Calendar): String

    /**
     * Formats the current time using a [DateTimeFormatter].
     * Throws an exception for API levels below 26.
     * @param formatter DateTimeFormatter instance
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun formatTime(formatter: DateTimeFormatter): String

    /**
     * Formats the current time using a [SimpleDateFormat].
     * @param formatter SimpleDateFormat instance
     */
    abstract fun formatTime(formatter: SimpleDateFormat): String

    /**
     * Calculates the difference between two dates using [LocalDate].
     * Throws an exception for API levels below 26.
     * @param fromDate Start date
     * @param toDate End date
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun findDateDifference(fromDate: LocalDate, toDate: LocalDate): JDateDifference

    /**
     * Calculates the difference between two dates using [Calendar].
     * @param fromDate Start date
     * @param toDate End date
     */
    abstract fun findDateDifference(fromDate: Calendar, toDate: Calendar): JDateDifference

    /**
     * Calculates the day difference between two dates using [LocalDate].
     * Throws an exception for API levels below 26.
     * @param fromDate Start date
     * @param toDate End date
     */
    @RequiresApi(Build.VERSION_CODES.O)
    abstract fun findDayDifference(fromDate: LocalDate, toDate: LocalDate): Long

    /**
     * Calculates the day difference between two dates using [Calendar].
     * @param fromDate Start date
     * @param toDate End date
     */
    abstract fun findDayDifference(fromDate: Calendar, toDate: Calendar): Long

    /**
     * Returns the length of a given month.
     * @param year Year of the month
     * @param month Month (0 for January to 11 for December)
     */
    abstract fun lengthOfMonth(year: Int, month: Int): Int

    /**
     * Calculates the epoch day of a given date.
     * @param year Year of the date
     * @param month Month (0 for January to 11 for December)
     * @param dayOfMonth Day of the month (1-31)
     */
    abstract fun findEpochDay(year: Int, month: Int, dayOfMonth: Int): Long

    /**
     * Determines the day of the week for a given date.
     * @param year Year of the date
     * @param month Month (0 for January to 11 for December)
     * @param dayOfMonth Day of the month (1-31)
     */
    abstract fun findDayOfWeek(year: Int, month: Int, dayOfMonth: Int): Int

    /**
     * Determines the day of the year for a given date.
     * @param year Year of the date
     * @param month Month (0 for January to 11 for December)
     * @param dayOfMonth Day of the month (1-31)
     */
    abstract fun findDayOfYear(year: Int, month: Int, dayOfMonth: Int): Int

    /**
     * Determines the day name of a given date as [JDay].
     * @param year Year of the date
     * @param month Month (0 for January to 11 for December)
     * @param dayOfMonth Day of the month (1-31)
     */
    abstract fun findDayName(year: Int, month: Int, dayOfMonth: Int): JDay

    /**
     * Converts a given time to total minutes.
     * @param hour Hour component of the time
     * @param minute Minute component of the time
     */
    abstract fun findTimeInMinute(hour: Int, minute: Int): Int

    /**
     * Determines if a given year is a leap year.
     * @param year Year to check
     */
    fun isLeapYear(year: Int): Boolean =
        (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
}
