package com.example.ucp2.ui.viewModelMK

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp
import com.example.ucp2.ui.viewModel.DosenVM
import com.example.ucp2.ui.viewModel.HomeDosenVM

object PenyediaMkVM {
    val Factory = viewModelFactory {

        initializer {
            MatkulVM(
                krsApp().containerApp.repoMk,
                krsApp().containerApp.repositoryDsn
            )
        }
        initializer {
            HomeMatkulVM(
                krsApp().containerApp.repoMk,
                krsApp().containerApp.repositoryDsn
            )
        }
        initializer {
            DetailMatkulVM(
                createSavedStateHandle(),
                krsApp().containerApp.repoMk
            )
        }
        initializer {
            UpdateMatkulVM(
                createSavedStateHandle(),
                krsApp().containerApp.repoMk,
                krsApp().containerApp.repositoryDsn
            )
        }
    }
}

fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)