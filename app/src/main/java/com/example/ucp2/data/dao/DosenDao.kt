package com.example.ucp2.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface DosenDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen)

    @Query ("Select * FROM dosen ORDER BY nama ASC")
    fun getAllDosen() : Flow<List<Dosen>>

    @Query("SELECT * FROM dosen WHERE Nidn = :Nidn")
    fun getDosen (Nidn: String): Flow<Dosen>
}