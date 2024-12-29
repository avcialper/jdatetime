package com.avcialper.jdatetime.model

import com.avcialper.jdatetime.util.JDay
import com.avcialper.jdatetime.util.JMonth

data class JDate(
    val date: String,
    val day: Int,
    val month: Int,
    val year: Int,
    val dayOfWeek: Int,
    val dayName: JDay,
    val monthName: JMonth
)