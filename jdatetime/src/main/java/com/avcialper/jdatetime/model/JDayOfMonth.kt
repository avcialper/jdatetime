package com.avcialper.jdatetime.model

data class JDayOfMonth(
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
     * @param otherDate The date to compare with.
     */
    fun isToday(otherDate: JDayOfMonth) = this == otherDate

}