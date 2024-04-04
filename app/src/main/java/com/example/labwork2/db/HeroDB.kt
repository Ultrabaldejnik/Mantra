package com.example.labwork2.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SuperHeroModel::class], version = 1,exportSchema = false)
abstract class HeroDB : RoomDatabase() {
    abstract val dao: SuperHeroDAO

    companion object {
        @Volatile
        private var Instance: HeroDB? = null


        fun getDatabase(context: Context): HeroDB {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HeroDB::class.java, "hero_database")

                    .build()
                    .also { Instance = it }
            }
        }
    }
}