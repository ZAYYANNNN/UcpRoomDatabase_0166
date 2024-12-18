package com.example.ucp2.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDsn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class HomeMhsViewModel(
    private val repositoryDsn: RepositoryDsn
) : ViewModel() {

    val homeUIState: StateFlow<HomeUiState> = repositoryDsn.getAllDosen()
        .filterNotNull()
        .map {
            HomeUiState(
                listDsn = it.toList(),
                isloading = false
            )
        }
        .onStart {
            emit(HomeUiState(isloading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isloading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isloading = true
            )
        )

}

data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isloading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)