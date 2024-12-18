package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MatkulDao
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.Matkul

@Database(entities = [Dosen::class],[Matkul::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {
    //Mendefinisikan fungsi untuk mengakses data Mahasiswa

    abstract fun dosenDao(): DosenDao
    abstract fun matkulDao(): MatkulDao

    companion object{
        @Volatile // Memastikan bahwa nilai variabel instance selalu sama di
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context):KrsDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java, //Class Database
                    "KrsDatabase" //Nama Database
                )
                    .build().also { Instance = it }
            })
        }
    }
}