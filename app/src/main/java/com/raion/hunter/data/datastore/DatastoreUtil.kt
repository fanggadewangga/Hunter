package com.raion.hunter.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreUtil {
    const val DATA_STORE_NAME = "HUNTER_DATASTORE"
    val HAVE_RUN_APP_BEFORE_PREF_KEY = booleanPreferencesKey("isFirstTime")
}