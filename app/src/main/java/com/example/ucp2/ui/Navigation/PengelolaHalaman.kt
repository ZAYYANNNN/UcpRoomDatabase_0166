package com.example.ucp2.ui.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.view.Dosen.HomeDosen
import com.example.ucp2.ui.view.Matkul.HomeMk

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {


    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { HalamanMenu(navController) }
        composable("dosen") { HomeDosen() }
        composable("mataKuliah") { HomeMk()}
    }
}

@Composable
fun HalamanMenu(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Menu Utama",
                onBack = { /* Tidak perlu aksi untuk halaman utama */ },
                showBackButton = false
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(
                onClick = { navController.navigate("dosen") },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Menu Dosen")
            }

            Button(
                onClick = { navController.navigate("mataKuliah") },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Menu Mata Kuliah")
            }
        }
    }
}
