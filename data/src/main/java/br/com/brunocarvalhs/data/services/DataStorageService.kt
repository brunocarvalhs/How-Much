package br.com.brunocarvalhs.data.services

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import br.com.brunocarvalhs.domain.services.IDataStorageService
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "app_preferences")

class DataStorageService(
    private val context: Context,
    private val gson: Gson = Gson()
) : IDataStorageService {
    override suspend fun saveValue(key: String, value: String) {
        val dataKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[dataKey] = value
        }
    }

    override suspend fun getValue(key: String): String? {
        val dataKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[dataKey]
    }

    override suspend fun <T> saveValue(key: String, value: T, clazz: Class<T>) {
        val jsonString = gson.toJson(value, clazz)
        saveValue(key, jsonString)
    }

    override suspend fun <T> getValue(key: String, clazz: Class<T>): T? {
        val jsonString = getValue(key) ?: return null
        return gson.fromJson(jsonString, clazz)
    }

    override suspend fun removeValue(key: String) {
        val dataKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(dataKey)
        }
    }

    override suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
