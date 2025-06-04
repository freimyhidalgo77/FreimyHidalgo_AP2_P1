package edu.ucne.freimyhidalgo_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.freimyhidalgo_p1_ap2.data.local.database.TareaDb
import edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas.SistemaListScreen
import edu.ucne.freimyhidalgo_p1_ap2.repository.TareaRepository
import kotlinx.coroutines.CoroutineScope

@Composable
fun HostNavigation(tareaDb:TareaDb,navHostController: NavHostController) {
    val scope:CoroutineScope
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner
    val tareaRepository:TareaRepository

    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ) {
        composable<Screen.List>{
            SistemaListScreen()
        }
    }
}