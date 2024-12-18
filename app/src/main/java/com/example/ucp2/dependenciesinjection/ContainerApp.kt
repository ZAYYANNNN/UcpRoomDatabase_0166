package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.database.KrsDatabase
import com.example.ucp2.repository.LocalRepoDsn
import com.example.ucp2.repository.LocalRepoMk
import com.example.ucp2.repository.RepoMk
import com.example.ucp2.repository.RepositoryDsn

interface InterfaceContainerApp {
    val repositoryDsn: RepositoryDsn //untuk memasukkan daftar repository jika repository yang di pakai banyak
    val repoMk: RepoMk
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDsn: RepositoryDsn by lazy {
        LocalRepoDsn(KrsDatabase.getDatabase(context).dosenDao())
    }
    override val repoMk: RepoMk by lazy {
        LocalRepoMk(KrsDatabase.getDatabase(context).matkulDao())
    }

}