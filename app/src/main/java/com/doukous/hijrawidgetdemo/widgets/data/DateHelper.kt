package com.doukous.hijrawidgetdemo.widgets.data

import android.icu.text.SimpleDateFormat
import android.icu.util.ULocale
import java.util.Date

data class DateHelper (
    val arLocale: ULocale = ULocale("ar_AR@calendar=islamic-civil"),
    val frLocale: ULocale = ULocale("fr_FR@calendar=islamic-civil"),

    var todayDate: Date = Date(),

    val arFormat: SimpleDateFormat = SimpleDateFormat("MMMM", arLocale),
    val frFormat: SimpleDateFormat = SimpleDateFormat("d MMMM y G", frLocale),

    val arDateStr: String = arFormat.format(todayDate),
    val frDateStr: String = frFormat.format(todayDate),
)