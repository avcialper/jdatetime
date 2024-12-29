package com.avcialper.jdatetime.datetime

import android.icu.text.SimpleDateFormat
import com.avcialper.jdatetime.JDateTime
import com.avcialper.jdatetime.model.JDateDifference
import com.avcialper.jdatetime.model.JDayOfMonth
import com.avcialper.jdatetime.util.JDay
import com.avcialper.jdatetime.util.JMonth
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class JCalendar : JDateTime() {

    private val calendar: Calendar
        get() = Calendar.getInstance()

    override val date: String
        get() = "$year.${month + 1}.$day"

    override val time: String
        get() = "$hour:$minute:$second"

    override val year: Int
        get() = useCalendarGet(Calendar.YEAR)

    override val month: Int
        get() = useCalendarGet(Calendar.MONTH)

    override val day: Int
        get() = useCalendarGet(Calendar.DAY_OF_YEAR)

    override val hour: Int
        get() = useCalendarGet(Calendar.HOUR_OF_DAY)

    override val minute: Int
        get() = useCalendarGet(Calendar.MINUTE)

    override val second: Int
        get() = useCalendarGet(Calendar.SECOND)

    override val epochDay: Long
        get() = calendar.timeInMillis / (24 * 60 * 60 * 1000)

    override val timeInSecond: Int
        get() = (hour * 3600) + (minute * 60) + second

    override val dayOfWeek: Int
        get() {
            val calendarDayOfWeek = useCalendarGet(Calendar.DAY_OF_WEEK) - 2
            return if (calendarDayOfWeek < 0)
                6
            else
                calendarDayOfWeek
        }

    override val dayOfMonth: Int
        get() = day

    override val dayOfYear: Int
        get() = useCalendarGet(Calendar.DAY_OF_YEAR)

    override val dayName: JDay
        get() = JDay.entries[dayOfWeek]

    override val weekOfMonth: Int
        get() = useCalendarGet(Calendar.WEEK_OF_MONTH)

    override val weekOfYear: Int
        get() = useCalendarGet(Calendar.WEEK_OF_YEAR)

    override val monthName: JMonth
        get() = JMonth.entries[month]

    override val isLeapYear: Boolean
        get() = super.isLeapYear(year)

    private fun useCalendarGet(property: Int): Int = calendar.get(property)

    override fun getAllDaysOfMonth(year: Int, month: Int): List<JDayOfMonth> {
        val calendar = Calendar.getInstance()
        calendar.clear()

        val days: MutableList<JDayOfMonth> = mutableListOf()

        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        var date: String
        var dayOfMonth: Int
        var dayOfWeek: Int
        var dayName: String
        var weekOfMonth: Int

        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (day in 2..maxDay + 1) {
            val currentYear = calendar.get(Calendar.YEAR)
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

            /*
            The first day of week is Sunday and index is 1.
            I can subtract 2 from the day of the week to get the correct index.
            0 - MONDAY
            6 - SUNDAY
             */
            val calendarDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2

            date = String.format(
                Locale("tr-TR"),
                "%02d.%02d.%04d",
                currentDay,
                currentMonth + 1,
                currentYear
            )
            dayOfWeek = if (calendarDayOfWeek == -1) 6 else calendarDayOfWeek
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            dayName = JDay.entries[dayOfWeek].name
            weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH)

            val jDayOfMonth = JDayOfMonth(
                date,
                dayOfMonth,
                dayName,
                dayOfWeek,
                weekOfMonth
            )
            days.add(jDayOfMonth)

            calendar.set(Calendar.DAY_OF_MONTH, day)
        }

        return days
    }

    override fun format(formatter: DateTimeFormatter): String {
        throw UnsupportedOperationException("This operation requires API level 26 or higher.")
    }

    override fun format(formatter: SimpleDateFormat): String {
        return formatter.format(calendar.time)
    }

    override fun formatDate(formatter: DateTimeFormatter, date: LocalDate): String {
        throw UnsupportedOperationException("This operation requires API level 26 or higher.")
    }

    override fun formatDate(formatter: SimpleDateFormat, date: Calendar): String {
        return formatter.format(date.time)
    }

    override fun formatTime(formatter: DateTimeFormatter): String {
        throw UnsupportedOperationException("This operation requires API level 26 or higher.")
    }

    override fun formatTime(formatter: SimpleDateFormat): String {
        return formatter.format(calendar.time)
    }

    override fun findDateDifference(fromDate: LocalDate, toDate: LocalDate): JDateDifference {
        throw UnsupportedOperationException("This operation requires API level 26 or higher.")
    }

    override fun findDateDifference(fromDate: Calendar, toDate: Calendar): JDateDifference {
        var years = toDate.get(Calendar.YEAR) - fromDate.get(Calendar.YEAR)
        var months = toDate.get(Calendar.MONTH) - fromDate.get(Calendar.MONTH)
        var days = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH)

        // If the difference between the months is negative, subtract 1 from the years and add 12 to the months.
        if (months < 0) {
            months += 12
            years--
        }

        // If the difference between the days is negative, subtract 1 from the months and add the number of days in the previous month to the days.
        if (days < 0) {
            val lastMonth = Calendar.getInstance()
            lastMonth.set(Calendar.YEAR, toDate.get(Calendar.YEAR))
            lastMonth.set(Calendar.MONTH, toDate.get(Calendar.MONTH) - 1)
            days += lastMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
            months--
        }

        return JDateDifference(years, months, days)
    }

    override fun findDayDifference(fromDate: LocalDate, toDate: LocalDate): Long {
        throw UnsupportedOperationException("This operation requires API level 26 or higher.")
    }

    override fun findDayDifference(fromDate: Calendar, toDate: Calendar): Long {
        val multiplier = 24 * 60 * 60 * 1000
        val epochDayOfFromDate = fromDate.timeInMillis / multiplier
        val epochDayOfToDate = toDate.timeInMillis / multiplier
        val dayDifference = epochDayOfToDate - epochDayOfFromDate
        return dayDifference
    }

    override fun lengthOfMonth(year: Int, month: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(year, month, 1)
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }
}
