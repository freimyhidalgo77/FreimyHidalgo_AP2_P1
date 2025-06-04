package edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas

sealed interface TareaEvent{

    data class TareaChange(val tareaId: Int):TareaEvent
    data class DescripcionChange(val descripcion:String):TareaEvent
    data class TiempoChange(val tiempo:Int):TareaEvent
    data object save:TareaEvent
    data object Delte:TareaEvent
    data object Nuevo:TareaEvent

}