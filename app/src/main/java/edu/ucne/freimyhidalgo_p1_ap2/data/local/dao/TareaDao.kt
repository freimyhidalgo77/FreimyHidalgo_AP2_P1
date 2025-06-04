package edu.ucne.freimyhidalgo_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.freimyhidalgo_p1_ap2.data.local.entity.TareaEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TareaDao {

    @Upsert
    suspend fun save(tarea:TareaEntity)

    @Query("Select * From Tareas Where tareaId =:id Limit 1")
    suspend fun find(id:Int):TareaEntity

    @Delete
    suspend fun delete(tarea:TareaEntity)

    @Query("Select * From Tareas")
    fun getAll(): Flow<List<TareaEntity>>

}