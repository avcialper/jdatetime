package com.avcialper.jdatetime.datetime

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.avcialper.jdatetime.JDateTime
import com.avcialper.jdatetime.model.JDateDifference
import com.avcialper.jdatetime.model.JDayOfMonth
import com.avcialper.jdatetime.util.JDay
import com.avcialper.jdatetime.util.JMonth
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class JLocalDateTime : JDateTime() {

    private val localDateTime: LocalDateTime
        get() = LocalDateTime.now()

    private val calendar: Calendar
        get() = Calendar.getInstance()

    override val date: String
        get() = localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

    override val time: String
        get() = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    override val year: Int
        get() = localDateTime.year

    override val month: Int
        get() = localDateTime.month.ordinal

    override val day: Int
        get() = localDateTime.dayOfMonth

    override val hour: Int
        get() = localDateTime.hour

    override val minute: Int
        get() = localDateTime.minute

    override val second: Int
        get() = localDateTime.second

    override val epochDay: Long
        get() = localDateTime.toLocalDate().toEpochDay()

    override val timeInSecond: Int
        get() = localDateTime.toLocalTime().toSecondOfDay()

    override val dayOfWeek: Int
        get() = localDateTime.dayOfWeek.ordinal

    override val dayOfMonth: Int
        get() = localDateTime.dayOfMonth

    override val dayOfYear: Int
        get() = localDateTime.dayOfYear

    override val dayName: JDay
        get() = JDay.entries[dayOfWeek]

    override val weekOfMonth: Int
        get() = localDateTime.get(WeekFields.of(Locale.getDefault()).weekOfMonth())

    override val weekOfYear: Int
        get() = localDateTime.get(WeekFields.of(Locale.getDefault()).weekOfYear())

    override val monthName: JMonth
        get() = JMonth.entries[month]

    override val isLeapYear: Boolean
        get() = super.isLeapYear(year)

    override fun getAllDaysOfMonth(year: Int, month: Int): List<JDayOfMonth> {
        val days: MutableList<JDayOfMonth> = mutableListOf()

        val firstDay = LocalDate.of(year, month + 1, 1)
        val lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth())

        var date: String
        var dayOfMonth: Int
        var dayOfWeek: Int
        var dayName: String
        var weekOfMonth: Int

        var currentDay = firstDay
        while (!currentDay.isAfter(lastDay)) {
            date = currentDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            dayOfMonth = currentDay.dayOfMonth
            dayOfWeek = currentDay.dayOfWeek.value - 1
            dayName = JDay.entries[dayOfWeek].name
            weekOfMonth = currentDay.get(WeekFields.of(Locale.getDefault()).weekOfMonth())

            val jDayOfMonth = JDayOfMonth(
                date,
                dayOfMonth,
                dayName,
                dayOfWeek,
                weekOfMonth
            )
            days.add(jDayOfMonth)

            currentDay = currentDay.plusDays(1)
        }

        return days
    }

    override fun format(formatter: DateTimeFormatter): String {
        return localDateTime.format(formatter)
    }

    override fun format(formatter: SimpleDateFormat): String {
        return formatter.format(calendar.time)
    }

    override fun formatDate(formatter: DateTimeFormatter, date: LocalDate): String {
        return date.format(formatter)
    }

    override fun formatDate(formatter: SimpleDateFormat, date: Calendar): String {
        return formatter.format(date.time)
    }

    override fun formatTime(formatter: DateTimeFormatter): String {
        return localDateTime.format(formatter)
    }

    override fun formatTime(formatter: SimpleDateFormat): String {
        return formatter.format(calendar.time)
    }

    override fun findDateDifference(fromDate: LocalDate, toDate: LocalDate): JDateDifference {
        val period = Period.between(fromDate, toDate)
        val years = period.years
        val months = period.months
        val days = period.days

        return JDateDifference(years, months, days)
    }

    override fun findDateDifference(fromDate: Calendar, toDate: Calendar): JDateDifference {
        return JCalendar().findDateDifference(fromDate, toDate)
    }

    override fun findDayDifference(fromDate: LocalDate, toDate: LocalDate): Long {
        val epochDayOfFromDate = fromDate.toEpochDay()
        val epochDayOfToDate = toDate.toEpochDay()
        val dayDifference = epochDayOfToDate - epochDayOfFromDate
        return dayDifference
    }

    override fun findDayDifference(fromDate: Calendar, toDate: Calendar): Long {
        return JCalendar().findDayDifference(fromDate, toDate)
    }

    override fun lengthOfMonth(year: Int, month: Int): Int {
        val date = LocalDate.of(year, month + 1, 1)
        return date.lengthOfMonth()
    }
}