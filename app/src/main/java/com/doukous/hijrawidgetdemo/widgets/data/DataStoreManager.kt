package com.doukous.hijrawidgetdemo.widgets.data

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val ARABIC_MONTH = stringPreferencesKey("arabic-month")
val FRENCH_DATE = stringPreferencesKey(name = "french-date")
val TIME = stringPreferencesKey(name = "test-time")

val Context.dataStore by preferencesDataStore(name = "hijri-date")

