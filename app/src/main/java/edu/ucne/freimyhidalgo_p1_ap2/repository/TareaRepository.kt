package edu.ucne.freimyhidalgo_p1_ap2.repository

import edu.ucne.freimyhidalgo_p1_ap2.data.local.dao.TareaDao
import edu.ucne.freimyhidalgo_p1_ap2.data.local.entity.TareaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TareaRepository @Inject constructor(
    val tareaDao: TareaDao

){
    suspend fun saveTarea(tarea: TareaEntity) = tareaDao.save(tarea)

    suspend fun findTarea(Id:Int):TareaEntity = tareaDao.find(Id)

    suspend fun deleteTarea(tarea:TareaEntity) = tareaDao.delete(tarea)

    fun getAll(): Flow<List<TareaEntity>> = tareaDao.getAll()

}