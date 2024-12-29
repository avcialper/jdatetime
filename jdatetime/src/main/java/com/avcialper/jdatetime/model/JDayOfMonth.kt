package com.avcialper.jdatetime.model

data class JDayOfMonth(
    /**
     * Date as a string in the format `dd.MM.yyyy`.
     */
    val date: String,
    /**
     * Day of the month. Start day `1`.
     */
    val day: Int,
    /**
     * Name of the day of the week.
     */
    val dayName: String,
    /**
     * Index of the day of the week. Is between 0 to 6.
     */
    val dayOfWeek: Int,
    /**
     * The week number within the month.
     *
     * This value is determined based on the first day of the week and the minimum number of days required
     * to count as the first week, according to the device's locale settings.
     * - The start of the week depends on the locale. For example:
     *   In Turkey, the first day of the week is typically Monday.
     * - The minimum number of days required to define the first week also depends on the locale (commonly 4 days).
     *
     * Example:
     * - If the first day of the month is Monday, it belongs to the 1st week.
     * - If the first day of the month is Thursday and at least 4 days are required for a valid week,
     *   it may fall into the 2nd week.
     */
    val weekOfMonth: Int
) {

}