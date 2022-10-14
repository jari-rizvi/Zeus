package com.teamx.zeus.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.teamx.zeus.constants.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreProvider(var context: Context) {

    //Create the dataStore

    //Create some keys

    companion object {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AppConstants.DataStore.DATA_STORE_NAME)
        val IS_LOCALIZATION_KEY = booleanPreferencesKey(AppConstants.DataStore.LOCALIZATION_KEY_NAME)
        val USER_NAME_KEY = stringPreferencesKey(AppConstants.DataStore.USER_NAME_KEY)
        val TOKEN = stringPreferencesKey(AppConstants.DataStore.TOKEN)
        val SAVE_ID = stringPreferencesKey(AppConstants.DataStore.SAVE_ID)
        val DETAILS = stringPreferencesKey(AppConstants.DataStore.DETAILS)
        val PAYMENT = intPreferencesKey(AppConstants.DataStore.PAYMENT)
        val AVATAR = stringPreferencesKey(AppConstants.DataStore.AVATAR)
        val NAME = stringPreferencesKey(AppConstants.DataStore.NAME)
    }

    //Store data
    suspend fun storeData(isLocalizationKey: Boolean, name: String,token: String, details:String, payment: Int) {
       context.dataStore.edit {
            it[IS_LOCALIZATION_KEY] = isLocalizationKey
            it[USER_NAME_KEY] = name
            it[TOKEN] = token
            it[DETAILS] = details
            it[PAYMENT] = payment
        }

    }

    //get Token by using this
    val token: Flow<String?> = context.dataStore.data.map {
        it[TOKEN]
    }

    val details: Flow<String?> = context.dataStore.data.map {
        it[DETAILS]
    }
    val avatar: Flow<String?> = context.dataStore.data.map {
        it[AVATAR]
    }

    val name: Flow<String?> = context.dataStore.data.map {
        it[NAME]
    }

    val payment : Flow<Int?> =   context.dataStore.data.map {
        it[PAYMENT]
    }


    //save payment method by using this functionn
    suspend fun savePaymentMethod(payment: Int){
        context.dataStore.edit {
            it[PAYMENT] = payment
        }
    }

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }

    //save id by using this functionn
    suspend fun saveUserID(SAVE_ID1: String) {
        context.dataStore.edit {
            it[SAVE_ID] = SAVE_ID1
        }
    }

    suspend fun saveUserDetails(firstname: String, email: String, avatar: String) {
        context.dataStore.edit {
            it[NAME] = firstname
            it[DETAILS] = email
            it[AVATAR] = avatar
        }
    }

    suspend fun removeAll() {
        context.dataStore.edit {
            it.remove(TOKEN)
            it.remove(DETAILS)
            it.remove(AVATAR)
            it.remove(NAME)

        }
    }

    suspend fun saveUserDetails(firstname: String){
        context.dataStore.edit {
            it[DETAILS] = firstname

        }
    }


    //Create an Localization flow
    val localizationFlow: Flow<Boolean> =  context.dataStore.data.map {
        it[IS_LOCALIZATION_KEY] ?: false
    }

}