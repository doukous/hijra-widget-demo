package com.doukous.hijrawidgetdemo.widgets.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.ULocale
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

data class DateHelper (
    val arLocale: ULocale = ULocale("ar_AR@calendar=islamic-civil"),
    val frLocale: ULocale = ULocale("fr_FR@calendar=islamic-civil"),

    val todayDate: Date = Date(),

    val arFormat: SimpleDateFormat = SimpleDateFormat("MMMM", arLocale),
    val frFormat: SimpleDateFormat = SimpleDateFormat("d MMMM y G", frLocale),
    val timeFormat: SimpleDateFormat = SimpleDateFormat("HH : mm", frLocale),

    val timeStr: String = timeFormat.format(todayDate),
    val arDateStr: String = arFormat.format(todayDate),
    val frDateStr: String = frFormat.format(todayDate),
)

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
        .withHour(0)
        .withMinute(0)
        .withSecond(0)

    if (targetTime.isBefore(actualTime))
        targetTime = targetTime.plusDays(1)

    val timeInMilli = targetTime
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()

    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        timeInMilli,
        pendingIntent
    )
}