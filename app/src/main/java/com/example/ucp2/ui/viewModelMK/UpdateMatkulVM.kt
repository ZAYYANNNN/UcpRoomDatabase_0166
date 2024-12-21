package com.example.ucp2.ui.viewModelMK

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Matkul
import com.example.ucp2.repository.RepoMk
import com.example.ucp2.ui.Navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMatkulVM(
    savedStateHandle: SavedStateHandle,
    private val repoMk: RepoMk
) : ViewModel() {

    var updateUIState by mutableStateOf(MkUIState())
        private set
    private val _kdMk: String = checkNotNull(savedStateHandle[DestinasiUpdate.KDMK])

    init {
        viewModelScope.launch{
            updateUIState = repoMk.getMatkul(_kdMk)
                .filterNotNull()
                .first()
                .toUIstateMk()
        }
    }

    fun updateState(matkulEvent: MatkulEvent){
        updateUIState = updateUIState.copy(
            matkulEvent = matkulEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.matkulEvent
        val errorState = FormErrorStateMk(
            kdMK = if (event.kdMK.isNotEmpty()) null else "Kode tidak boleh kosong",
            namaMK = if(event.namaMK.isNotEmpty()) null else "Matakuliah tidak boleh kosong",
            sks = if(event.sks.isNotEmpty()) null else "jenis kelamin tidak boleh kosong",
            smstr = if(event.smstr.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if(event.jenis.isNotEmpty()) null else "Jenis Matakuliah tidak boleh kosong",
            dospem = if(event.dospem.isNotEmpty()) null else "Pengampu tidak boleh kosong",

            )
        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.matkulEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repoMk.updateMatkul(currentEvent.toMatkulEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil di update",
                        matkulEvent = MatkulEvent(),
                        isEntryValid = FormErrorStateMk()
                    )
                    println("SnackBarMessage diatur: ${updateUIState.
                    snackBarMessage}")
                }catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal di update"

                    )

                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }
    fun resetsnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}
fun Matkul.toUIstateMk(): MkUIState = MkUIState(
    matkulEvent = this.toDetailUiEvent(),
)