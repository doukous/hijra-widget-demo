package com.doukous.hijrawidgetdemo.widgets.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.ULocale
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.chrono.HijrahDate
import java.time.temporal.ChronoField
import java.util.Date

class AppDate {
    val daysInitials = listOf("M", "T", "W", "T", "F", "S", "S")
    val arLocale: ULocale = ULocale("ar_AR@calendar=islamic-umalqura")
    val enLocale: ULocale = ULocale("en_US@calendar=islamic-umalqura")
    val todayDate: Date = Date()
    private val arFormat: SimpleDateFormat = SimpleDateFormat("MMMM", arLocale)
    private val enFormat: SimpleDateFormat = SimpleDateFormat("d MMMM y G", enLocale)
    private val timeFormat: SimpleDateFormat = SimpleDateFormat("HH : mm", enLocale)

    val timeStr: String = timeFormat.format(todayDate)
    val arMonthStr: String = arFormat.format(todayDate)
    val enMonthStr: String = enFormat.format(todayDate)
    val dateStr: String = enFormat.format(todayDate)

    val todayDateHijrah = HijrahDate.now()
    val hijraDay = todayDateHijrah.get(ChronoField.DAY_OF_MONTH)

    fun getDateWithCustomFormat(pattern: String = "d MMMM y", dateLocal: ULocale = enLocale) : String {
        val customFormat = SimpleDateFormat(pattern, dateLocal)
        return customFormat.format(todayDate)
    }
}


fun setUpAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, DateAlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    val actualTime = LocalDateTime.now()

    var targetTime = actualTime
        .withHour(16)
        .withMinute(35)
        .withSecond(0)

    if (targetTime.isBefore(actualTime))
        targetTime = targetTime.plusDays(1)

    val timeInMilli = targetTime
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        timeInMilli,
        pendingIntent
    )
}