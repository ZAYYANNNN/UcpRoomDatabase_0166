package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class dosenDatabase : RoomDatabase() {
    abstract fun dosenDao(): DosenDao

    companion object{
        @Volatile // Memastikan bahwa nilai variabel instance selalu sama di
        private var Instance: dosenDatabase? = null

        fun getDatabase(context: Context): dosenDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    dosenDatabase::class.java, //Class Database
                    "KrsDatabase" //Nama Database
                )
                    .build().also { Instance = it }
            })

        }
    }
}