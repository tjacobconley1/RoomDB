package com.example.roomdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Marks a class as a RoomDatabase.
// The class should be an abstract
// class and extend RoomDatabase
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao


    // THIS IS A SINGLETON
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        // either uses existing database
        // or creates a new one if none exist
        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // everything in this block is protected from
            // concurent execution by multiple threads
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user+database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}