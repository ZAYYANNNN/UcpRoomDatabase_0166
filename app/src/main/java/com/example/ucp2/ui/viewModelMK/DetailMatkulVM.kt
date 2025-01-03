package com.example.ucp2.ui.viewModelMK

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Matkul
import com.example.ucp2.repository.RepoMk
import com.example.ucp2.repository.RepositoryDsn
import com.example.ucp2.ui.Navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMatkulVM(
    savedStateHandle: SavedStateHandle,
    private val repoMk: RepoMk,
) : ViewModel(){
    private val _kdMk: String = checkNotNull(savedStateHandle[DestinasiDetail.KDMK])

    val detailUiState: StateFlow<DetailUiState> = repoMk.getMatkul(_kdMk)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    erorMessage = it.message ?: "Terjadi Kesalahan",

                    )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )

    fun deleteMK(){
        detailUiState.value.detailUiEvent.toMatkulEntity().let {
            viewModelScope.launch {
                repoMk.deleteMatkul(it)
            }
        }
    }
}




data class DetailUiState(
    val detailUiEvent : MatkulEvent = MatkulEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val erorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MatkulEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MatkulEvent()

}

/* Data class untuk menampung data yang akan ditampilkan di UI*/

//memindahkan data dari entity ke ui
fun Matkul.toDetailUiEvent(): MatkulEvent{
    return MatkulEvent(
        kdMk = kdMk,
        namaMk = namaMk,
        sks = sks,
        smstr = smstr,
        jenis = jenis,
        dospem = dospem
    )
}