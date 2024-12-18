package com.example.ucp2.ui.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.launch




data class DosenEvent(
    val Nidn: String = "",
    val Nama: String = "",
    val jenisKelamin: String = "",

)

//Menyimpan input form ke dalam entity
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    Nidn = Nidn, //yang kiri punya entitas, yang kanan punya event
    Nama = Nama,
    jenisKelamin = jenisKelamin,
)
