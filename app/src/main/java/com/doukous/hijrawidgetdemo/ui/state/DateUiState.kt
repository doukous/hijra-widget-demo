package com.doukous.hijrawidgetdemo.ui.state

import android.icu.text.SimpleDateFormat
import android.icu.util.ULocale
import java.util.Date

data class DateUiState(
    val arLocale: ULocale = ULocale("ar_AR@calendar=islamic"),
    val frLocale: ULocale = ULocale("fr_FR@calendar=islamic"),

    val todayDate: Date = Date(),
    val arFormat: SimpleDateFormat = SimpleDateFormat("MMMM", arLocale),
    val frFormat: SimpleDateFormat = SimpleDateFormat("d MMMM y G", frLocale),
    val timeFormat: SimpleDateFormat = SimpleDateFormat("HH : mm : ss", frLocale),

    val arDateStr: String = arFormat.format(todayDate),
    val frDateStr: String = frFormat.format(todayDate),
    val time: String = timeFormat.format(todayDate),
)
