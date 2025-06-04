package edu.ucne.freimyhidalgo_p1_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tareas")
data class TareaEntity(
    @PrimaryKey(autoGenerate = true)

    var tareaId:Int? = null,
    var descripcion:String = "",
    var tiempo:Int? = null




)
