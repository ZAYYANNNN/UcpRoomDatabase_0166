package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepoDsn(private val dosenDao: DosenDao
) : RepositoryDsn
     {
        override suspend fun insertDosen(dosen: Dosen) {
            dosenDao.insertDosen(dosen)
        }

        override fun getAllDosen(): Flow<List<Dosen>> {
            return dosenDao.getAllDosen()
        }

        override fun getDosen(Nidn: String): Flow<Dosen> {
            return dosenDao.getDosen(Nidn)
        }

}