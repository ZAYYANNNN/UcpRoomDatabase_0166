package com.example.ucp2.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.launch

class  DosenVM(private val repositoryDsn: RepositoryDsn) : ViewModel() {
    var uiState by mutableStateOf(DosenUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent,

            )
    }
    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            Nidn = if(event.Nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            Nama = if (event.Nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if(event.jenisKelamin.isNotEmpty()) null else "jenis kelamin tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()

    }
    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDsn.insertDosen(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dosenEvent = DosenEvent(), //reset input form
                        isEntryValid = FormErrorState() //Reset error State
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "input tidak valid. periksa kembali data anda"
            )
        }

    }
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
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
