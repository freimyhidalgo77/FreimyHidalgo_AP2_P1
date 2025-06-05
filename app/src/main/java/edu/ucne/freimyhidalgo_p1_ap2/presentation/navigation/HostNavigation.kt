package edu.ucne.freimyhidalgo_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.freimyhidalgo_p1_ap2.data.local.database.TareaDb
import edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas.TareaDeleteScreen
import edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas.TareaEditBodyScreen
import edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas.TareaEditScreen
import edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas.TareaListScreen
import edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas.TareaScreen
import edu.ucne.freimyhidalgo_p1_ap2.repository.TareaRepository
import kotlinx.coroutines.CoroutineScope

@Composable

fun HostNavigation(taraeDb:TareaDb, navHostController: NavHostController){

    val scope = rememberCoroutineScope()
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner
    val tareaRepository: TareaRepository

    NavHost(
        navController = navHostController,
        startDestination = Screen.TareaList
    ) {
        composable<Screen.TareaList>{
            TareaListScreen(
                scope = scope,
                onCreate = {
                    navHostController.navigate(Screen.Tareas(0))
                },
                onEdit = {tarea->
                    navHostController.navigate(Screen.EditTareaScreen(tarea))
                },

                onDelete = {tarea->
                    navHostController.navigate(Screen.DeleteTareaScreen(tarea))

                }
            )
        }

        composable<Screen.Tareas>{
            val args = it.toRoute<Screen.Tareas>()
            TareaScreen(
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.EditTareaScreen>{
            val args = it.toRoute<Screen.EditTareaScreen>()
            TareaEditScreen(
                tareaId = args.tareaId!!,
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.DeleteTareaScreen>{
            val args = it.toRoute<Screen.DeleteTareaScreen>()
            TareaDeleteScreen (
                tareaId = args.tareaId!!,
                goBack = {
                    navHostController.navigateUp()

                }
            )
        }
    }
}