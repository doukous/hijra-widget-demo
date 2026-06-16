package com.doukous.hijrawidgetdemo.widgets.data

import android.content.Context

class DatastoreRepository(
    private val context: Context
) {
    suspend fun initialize() {
        updateDates()
    }

    suspend fun updateDates() {
        val date = DateHelper()

        context.dataStore.updateData {
            it.toMutablePreferences().also {
                prefs ->
                prefs[FRENCH_DATE] = date.frDateStr
                prefs[ARABIC_MONTH] = date.arDateStr
                prefs[TIME] = date.timeStr
            }
        }
    }
}