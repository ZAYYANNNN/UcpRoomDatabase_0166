package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MatkulDao
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.Matkul
import kotlinx.coroutines.flow.Flow

class LocalRepoMk(
    private val matkulDao: MatkulDao,
    private val dosenDao: DosenDao // Tambahkan ini
) : RepoMk {
    override suspend fun insertMatkul(matkul: Matkul) {
        matkulDao.insertMatkul(matkul)
    }

    override fun getAllMatkul(): Flow<List<Matkul>> {
        return matkulDao.getAllMatkul()
    }

    override fun getMatkul(kdMk: String): Flow<Matkul> {
        return matkulDao.getMatkul(kdMk)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen() // Gunakan objek dosenDao dari konstruktor
    }

    override suspend fun deleteMatkul(matkul: Matkul) {
        matkulDao.deleteMatkul(matkul)
    }

    override suspend fun updateMatkul(matkul: Matkul) {
        matkulDao.updateMatkul(matkul)
    }
}
