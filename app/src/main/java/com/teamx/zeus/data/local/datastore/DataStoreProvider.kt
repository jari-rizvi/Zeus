package com.teamx.zeus.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.teamx.zeus.constants.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreProvider(var context: Context) {

    //Create the dataStore
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AppConstants.DataStore.DATA_STORE_NAME)

    //Create some keys
    companion object {
        val IS_LOCALIZATION_KEY = booleanPreferencesKey(AppConstants.DataStore.LOCALIZATION_KEY_NAME)
        val USER_NAME_KEY = stringPreferencesKey(AppConstants.DataStore.USER_NAME_KEY)
        val TOKEN = stringPreferencesKey(AppConstants.DataStore.TOKEN)
        val DETAILS = stringPreferencesKey(AppConstants.DataStore.DETAILS)
    }

    //Store data
    suspend fun storeData(isLocalizationKey: Boolean, name: String,token: String, details:String) {
       context.dataStore.edit {
            it[IS_LOCALIZATION_KEY] = isLocalizationKey
            it[USER_NAME_KEY] = name
            it[TOKEN] = token
            it[DETAILS] = details
        }

    }


    //get Token by using this
    val token : Flow<String?> =  context.dataStore.data.map {
        it[TOKEN]
    }

    val details : Flow<String?> =   context.dataStore.data.map {
        it[DETAILS]
    }

    //save token by using this functionn
    suspend fun saveUserToken(token: String){
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun saveUserDetails(firstname: String,email: String){
        context.dataStore.edit {
            it[DETAILS] = firstname
            it[DETAILS] = email
        }
    }


    //Create an Localization flow
    val localizationFlow: Flow<Boolean> =  context.dataStore.data.map {
        it[IS_LOCALIZATION_KEY] ?: false
    }

}