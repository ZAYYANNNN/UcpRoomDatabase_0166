package com.example.ucp2.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.launch

class  MahasiswaViewModel(private val repositoryDsn: RepositoryDsn) : ViewModel() {
    var uiState by mutableStateOf(DosenUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent,

            )
    }
}
data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null ,
)

data class FormErrorState(
    val Nidn: String? = null,
    val Nama: String? = null,
    val jenisKelamin: String? = null
){
    fun isValid(): Boolean {
        return Nidn == null && Nama == null && jenisKelamin == null
    }

}


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
