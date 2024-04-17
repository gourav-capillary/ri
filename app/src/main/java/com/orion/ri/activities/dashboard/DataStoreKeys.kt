package com.orion.ri.activities.dashboard

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

class DataStoreKeys {
    val EXAMPLE_KEY = "example_key"

    companion object {
        val CURRENT_USER_DATA: Preferences.Key<String> = stringPreferencesKey("user_data")
        val PROJECTS_LIST: Preferences.Key<String> = stringPreferencesKey("projects_list")
        val EMPLOYEES_LIST: Preferences.Key<String> = stringPreferencesKey("employees_list")
        val EXAMPLE_KEY: Preferences.Key<String> = stringPreferencesKey("Example")
        val DITTO_KEY: Preferences.Key<String> = stringPreferencesKey("DITTO")
        val USER_TYPE: Preferences.Key<String> = stringPreferencesKey("user_type")
    }
}
