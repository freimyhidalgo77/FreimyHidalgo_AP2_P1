package edu.ucne.freimyhidalgo_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.freimyhidalgo_p1_ap2.presentation.sistemas.SistemaListScreen

@Composable
fun HostNavigation(
    navHostController: NavHostController,

    ) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ) {
        composable<Screen.List>{
            SistemaListScreen()
        }
    }
}