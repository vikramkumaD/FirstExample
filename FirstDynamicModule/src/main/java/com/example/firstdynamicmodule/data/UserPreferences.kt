package com.example.firstdynamicmodule.data


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")


class UserPreferences {

   // private val appContext = context.applicationContext

   /* suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }*/



   /*val getUserName : kotlinx.coroutines.flow.Flow<String?> get() = appContext.dataStore.data.map {  preferences ->
            preferences[USERNAME]
   }

    suspend fun saveUserData(username: String) {
        appContext.dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    companion object {
        private val USERNAME = stringPreferencesKey("username")
    }*/
}