package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.MatkulDao
import com.example.ucp2.data.entity.Matkul

@Database(entities = [Matkul::class], version = 1, exportSchema = false)
abstract class matkulDatabase : RoomDatabase() {
    abstract fun matkulDao(): MatkulDao

    companion object{
        @Volatile // Memastikan bahwa nilai variabel instance selalu sama di
        private var Instance: matkulDatabase? = null

        fun getDatabase(context: Context): matkulDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    matkulDatabase::class.java, //Class Database
                    "matkulDatabase" //Nama Database
                )
                    .build().also { Instance = it }
            })

        }
    }
}