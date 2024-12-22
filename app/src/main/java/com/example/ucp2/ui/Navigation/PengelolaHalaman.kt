package com.example.ucp2.ui.Navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.view.Dosen.DestinasiInsert
import com.example.ucp2.ui.view.Dosen.HomeDosen
import com.example.ucp2.ui.view.Dosen.InsertDosen
import com.example.ucp2.ui.view.Matkul.DestinasiInsertMk
import com.example.ucp2.ui.view.Matkul.DetailMatkul
import com.example.ucp2.ui.view.Matkul.HomeMk
import com.example.ucp2.ui.view.Matkul.InsertMatkul
import com.example.ucp2.ui.view.Matkul.UpdateMatkul
import com.example.ucp2.R

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "HalamanMenu") {
        composable(
            route = "HalamanMenu"
        ) {
            HalamanMenu(navController = navController)
        }

        composable(
            route = DestinasiHomeDosen.route
        ) {
            HomeDosen(
                onAddDsn = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }


        composable(
            route = DestinasiInsert.route
        ) {
            InsertDosen(
                onBack = { navController.popBackStack() }, // Navigasi kembali
                onNavigate = {}
            )
        }

        composable(
            route = DestinasiHomeMatkul.route
        ) {
            HomeMk(
                onDetailClick = { kdMk ->
                    navController.navigate("${DestinasiDetail.route}/$kdMk")
                    println("PengelolaHalaman: kdMk = $kdMk")
                },
                onAddMk = {
                    navController.navigate(DestinasiInsertMk.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertMk.route
        ) {
            InsertMatkul(
                onBack = { navController.popBackStack() }, // Navigasi kembali
                onNavigate = {}
            )
        }

        composable(
            route = DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.KDMK) {
                    type = NavType.StringType
                }
            )
        ) {
            val kdMk = it.arguments?.getString(DestinasiDetail.KDMK)
            kdMk?.let { kdMk ->
                DetailMatkul(
                    onBack = { navController.popBackStack() },
                    onEditClick = { navController.navigate("${DestinasiUpdate.route}/$it") },
                    onDeleteClick = { navController.popBackStack() },
                    modifier = modifier
                )
            }
        }

        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.KDMK) {
                    type = NavType.StringType
                }
            )
        ) {

            UpdateMatkul(
                // Pass the list to UpdateMatkul
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

    }
}

@Composable
fun HalamanMenu(navController: NavHostController) {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Logo Gundar di atas
            Image(
                painter = painterResource(id = R.drawable.gundar2),
                contentDescription = "Logo Gundar",
                modifier = Modifier
                    .size(200.dp) // Memperbesar ukuran gambar
                    .align(Alignment.CenterHorizontally) // Menempatkan gambar di tengah
                    .padding(top = 16.dp) // Menambahkan padding top untuk memberikan jarak dengan bagian atas layar
            )

            // Tombol-tombol di bawah logo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp) // Menambahkan padding top setelah logo agar tombol tidak terlalu rapat
            ) {
                Button(
                    onClick = { navController.navigate(DestinasiHomeDosen.route) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = "Menu Dosen",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(DestinasiHomeMatkul.route) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = "Menu Mata Kuliah",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
