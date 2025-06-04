package edu.ucne.freimyhidalgo_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data class Tareas(val tareaId: Int?): Screen()

    @Serializable
    data class EditTareaScreen(val tareaId:Int?): Screen()

    @Serializable
    data class DeleteTareaScreen(val tareaId:Int?):Screen

    @Serializable
    data object TareaList: Screen()
}