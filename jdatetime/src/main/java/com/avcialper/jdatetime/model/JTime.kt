package com.avcialper.jdatetime.model

data class JTime(
    /** Time as a string in the format `HH:mm:ss` */
    val time: String,
    /** Hour of the day */
    val hour: Int,
    /** Minute of the hour */
    val minute: Int,
    /** Second of the minute */
    val second: Int
) {

    /**
     * Checks if the time is after another time.
     * @param other The time to compare with.
     * @return `true` if the time is after the other time, `false` otherwise.
     */
    fun isAfter(other: JTime): Boolean {
        return hour > other.hour || (hour == other.hour && minute > other.minute) ||
                (hour == other.hour && minute == other.minute && second > other.second)

    }

    /**
     * Checks if the time is before another time.
     * @param other The time to compare with.
     * @return `true` if the time is before the other time, `false` otherwise.
     */
    fun isBefore(other: JTime): Boolean {
        return hour < other.hour || (hour == other.hour && minute < other.minute) ||
                (hour == other.hour && minute == other.minute && second < other.second)

    }

    fun findTimeDifference(other: JTime): JTimeDifference {
        val isThisGreater = this.isAfter(other)

        val biggerTime = if (isThisGreater) this else other
        val smallerTime = if (isThisGreater) other else this

        var hourDiff = biggerTime.hour - smallerTime.hour
        var minuteDiff = biggerTime.minute - smallerTime.minute
        var secondDiff = biggerTime.second - smallerTime.second

        if (secondDiff < 0) {
            secondDiff += 60
            minuteDiff--
        }

        if (minuteDiff < 0) {
            minuteDiff += 60
            hourDiff--
        }

        return JTimeDifference(hourDiff, minuteDiff, secondDiff)
    }
}