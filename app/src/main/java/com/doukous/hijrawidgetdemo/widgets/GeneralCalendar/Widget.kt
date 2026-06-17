package com.doukous.hijrawidgetdemo.widgets.GeneralCalendar

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.doukous.hijrawidgetdemo.R
import com.doukous.hijrawidgetdemo.widgets.data.AppDate


class Widget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appDate = AppDate()
        val daysList = appDate.getDaysList()

        provideContent {
            WidgetContent(appDate, daysList)
        }
    }

    @Composable
    fun WidgetContent(appDate: AppDate, daysList: MutableList<Int?>) {
        Column(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = GlanceModifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            Row(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .height(24.dp)
            ){
                Text(
                    appDate.enMonthStr.uppercase(),
                )

                Spacer(
                    GlanceModifier
                        .width(24.dp)
                )

                Text(
                    appDate.arMonthStr,
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }

            Row(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                appDate.daysInitials.map { letter ->
                    Text(
                        letter,
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = ColorProvider(day=Color.Gray, night=Color.Gray)
                        ),
                        modifier = GlanceModifier
                            .size(24.dp)
                    )
                }
            }

            (0..4).map { week ->
                Column(modifier = GlanceModifier) {
                    Row(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        daysList
                            .subList(week * 7, (week + 1) * 7)
                            .map { day ->
                            if (day == appDate.dayNumber)
                                Text(
                                    day.toString(),
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        color = ColorProvider(day=Color.White, night = Color.White)
                                    ),
                                    modifier = GlanceModifier
                                        .size(24.dp)
                                        .background(R.color.teal_700)
                                        .cornerRadius(8.dp)
                                )
                            else
                                Text(
                                    day?.toString() ?: "",
                                    style = TextStyle(textAlign = TextAlign.Center),
                                    modifier = GlanceModifier
                                        .size(24.dp)
                                )
                        }
                    }
                }
            }
        }
    }
}