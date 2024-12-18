package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.Matkul
import kotlinx.coroutines.flow.Flow

@Dao
interface MatkulDao {
    @Insert
    suspend fun insertMatkul(matkul: Matkul)

    @Query ("Select * FROM matkul ORDER BY namaMK ASC")
    fun getAllMatkul() : Flow<List<Matkul>>

    @Query("SELECT * FROM matkul WHERE kdMK = :kdMK")
    fun getMatkul (kdMK: String): Flow<Matkul>

    @Delete
    suspend fun deleteMatkul(matkul: Matkul)

    @Update
    suspend fun updateMatkul(matkul: Matkul)
}