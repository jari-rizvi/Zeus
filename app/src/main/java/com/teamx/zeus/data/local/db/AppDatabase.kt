package com.teamx.zeus.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teamx.zeus.constants.AppConstants
import com.teamx.zeus.data.local.ProductConverter
import com.teamx.zeus.data.local.TypeConverterMV
import com.teamx.zeus.data.local.UserConverter
import com.teamx.zeus.data.models.MusicModel
import com.teamx.zeus.data.local.dbModel.CartDao
import com.teamx.zeus.data.local.dbModel.CartTable
import com.teamx.zeus.data.local.dbModel.UserDao
import com.teamx.zeus.data.local.dbModel.UserTable


@Database(entities = [MusicModel::class, CartTable::class,  UserTable::class],
    version = 4,
    exportSchema = false
)

@TypeConverters(TypeConverterMV::class, ProductConverter::class, UserConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun appDao(): AppDao
    abstract fun cartDao(): CartDao
    abstract fun userDao(): UserDao


    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance
                ?: synchronized(this) { instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, AppConstants.DbConfiguration.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}