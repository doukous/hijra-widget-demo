package com.doukous.hijrawidgetdemo.widgets.TodayDate

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
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
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.doukous.hijrawidgetdemo.R
import com.doukous.hijrawidgetdemo.ui.state.DateUiState

class Widget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent()
        }
    }

    @Composable
    fun WidgetContent() {
        val dateUiState = DateUiState()

        Column(
            GlanceModifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextContent(
                dateUiState.arDateStr,
                color = ColorProvider(Color.White, Color.White),
                textFontSize = 18.sp,
                modifier = GlanceModifier
                    .background(R.color.teal_700)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
            Spacer(
                GlanceModifier
                    .height(4.dp)
            )
            TextContent(
                dateUiState.frDateStr.uppercase(),
                modifier = GlanceModifier
                    .background(Color.White)
                    .padding(6.dp)
                    .fillMaxWidth()
            )
        }
    }

    @Composable
    private fun TextContent(
        text: String,
        color: ColorProvider = ColorProvider(Color.Black, Color.Black),
        textFontSize: TextUnit = 14.sp,
        modifier: GlanceModifier = GlanceModifier.Companion
    ) {
        Text(
            text,
            style = TextStyle(
                color = color,
                fontSize = textFontSize,
                textAlign = TextAlign.Center
            ),
            modifier = modifier
                .cornerRadius(12.dp)
        )
    }
}