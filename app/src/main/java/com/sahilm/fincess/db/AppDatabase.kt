package com.sahilm.fincess.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sahilm.fincess.model.Transaction

@Database(entities = [Transaction::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "fincess_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}