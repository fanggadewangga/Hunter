package com.raion.hunter.data.datastore

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HunterDatastore(private val context: Context) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: HunterDatastore? = null

        fun getInstance(context: Context): HunterDatastore =
            instance ?: synchronized(this) {
                val newInstance = instance ?: HunterDatastore(context).also { instance = it }
                newInstance
            }
    }

    private val Context.hunterPreferenceDatastore: DataStore<Preferences> by preferencesDataStore(
        name = DataStoreUtil.DATA_STORE_NAME
    )

    suspend fun savePrefHaveRunAppBefore(isFirstTime: Boolean) {
        context.hunterPreferenceDatastore.edit {
            it[DataStoreUtil.HAVE_RUN_APP_BEFORE_PREF_KEY] = isFirstTime
        }
    }

    fun readPrefHaveRunAppBefore(): Flow<Boolean> = context.hunterPreferenceDatastore.data.map {
        it[DataStoreUtil.HAVE_RUN_APP_BEFORE_PREF_KEY] ?: false
    }
}

