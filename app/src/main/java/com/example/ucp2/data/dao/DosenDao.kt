package com.example.ucp2.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface DosenDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen)

    @Query ("Select * FROM dosen ORDER BY nama ASC")
    fun getAllMahasiswa() : Flow<List<Dosen>>

    @Query("SELECT * FROM dosen WHERE Nidn = :Nidn")
    fun getMahasiswa (Nidn: String): Flow<Dosen>
}