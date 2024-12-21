package com.example.ucp2.ui.viewModelMK

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Matkul
import com.example.ucp2.repository.RepoMk
import kotlinx.coroutines.launch
import kotlin.String

class MatkulVM (private val repoMk: RepoMk): ViewModel() {
    var uiStateMk by mutableStateOf(MkUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateStateMk(matkulEvent: MatkulEvent) {
        uiStateMk = uiStateMk.copy(
            matkulEvent = matkulEvent,

            )
    }
    private fun validateFields(): Boolean {
        val event = uiStateMk.matkulEvent
        val errorState = FormErrorStateMk(
            kdMK = if(event.kdMK.isNotEmpty()) null else "Kode tidak boleh kosong",
            namaMK = if (event.namaMK.isNotEmpty()) null else "Nama Matkul tidak boleh kosong",
            sks = if(event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            smstr = if(event.smstr.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if(event.jenis.isNotEmpty()) null else "Jenis Matkul tidak boleh kosong",
            dospem = if(event.dospem.isNotEmpty()) null else "Dospem tidak boleh kosong",
        )
        uiStateMk = uiStateMk.copy(isEntryValid = errorState)
        return errorState.isValid()

    }
    fun saveData() {
        val currentEvent = uiStateMk.matkulEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repoMk.insertMatkul(currentEvent.toMatkulEntity())
                    uiStateMk = uiStateMk.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matkulEvent = MatkulEvent(), //reset input form
                        isEntryValid = FormErrorStateMk() //Reset error State
                    )
                } catch (e: Exception) {
                    uiStateMk = uiStateMk.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiStateMk = uiStateMk.copy(
                snackBarMessage = "input tidak valid. periksa kembali data anda"
            )
        }

    }
    fun resetSnackBarMessage() {
        uiStateMk = uiStateMk.copy(snackBarMessage = null)
    }
}

data class MkUIState(
    val matkulEvent: MatkulEvent = MatkulEvent(),
    val isEntryValid: FormErrorStateMk = FormErrorStateMk(),
    val snackBarMessage: String? = null,
)

data class FormErrorStateMk(
    val kdMK : String? = null,
    val namaMK: String? = null,
    val sks: String? = null,
    val smstr: String? = null,
    val jenis: String? = null,
    val dospem: String? = null
){
    fun isValid(): Boolean {
        return kdMK == null && namaMK == null && sks == null &&
                smstr == null && jenis == null && dospem == null
    }

}

data class MatkulEvent(
    val kdMK : String = "",
    val namaMK: String = "",
    val sks: String = "",
    val smstr: String = "",
    val jenis: String = "",
    val dospem: String = ""

    )

//Menyimpan input form ke dalam entity
fun MatkulEvent.toMatkulEntity(): Matkul = Matkul(
    kdMK = kdMK, //yang kiri punya entitas, yang kanan punya event
    namaMK = namaMK,
    sks =  sks,
    smstr =  smstr,
    jenis = jenis,
    dospem = dospem
)
