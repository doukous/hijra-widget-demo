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
            it.toMutablePreferences().also { preferences ->
                preferences[FRENCH_DATE] = date.frDateStr
                preferences[ARABIC_MONTH] = date.arDateStr
            }
        }
    }
}