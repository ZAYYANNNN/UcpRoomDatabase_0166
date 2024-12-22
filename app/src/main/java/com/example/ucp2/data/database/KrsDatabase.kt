package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MatkulDao
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.Matkul

@Database(entities = [Dosen::class, Matkul::class], version = 2, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {
    // Mendefinisikan fungsi DAO
    abstract fun dosenDao(): DosenDao
    abstract fun matkulDao(): MatkulDao

    companion object {
        @Volatile
        private var Instance: KrsDatabase? = null

        // Definisi migrasi dari versi 1 ke 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Contoh: Menambahkan kolom baru pada tabel Matkul
                database.execSQL("ALTER TABLE Matkul ADD COLUMN sks INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): KrsDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java,
                    "KrsDatabase"
                )
                    .fallbackToDestructiveMigration() // Hapus database lama jika versi berubah
                    .build()

            })
        }
    }
}
