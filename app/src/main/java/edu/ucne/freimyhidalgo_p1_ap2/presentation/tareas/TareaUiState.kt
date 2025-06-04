package edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas

import edu.ucne.freimyhidalgo_p1_ap2.data.local.entity.TareaEntity

data class UiState (
    val tareaId: Int? = null,
    val descripcion:String = "",
    val tiempo:Int? = null,
    val successMessage:String? = null,
    val errorMessage:String? = null,
    val tareas:List<TareaEntity> = emptyList()
)