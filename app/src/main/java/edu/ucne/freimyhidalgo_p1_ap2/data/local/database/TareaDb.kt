package edu.ucne.freimyhidalgo_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.freimyhidalgo_p1_ap2.data.local.dao.TareaDao
import edu.ucne.freimyhidalgo_p1_ap2.data.local.entity.TareaEntity


@Database(

    entities = [
        TareaEntity::class
    ],
    version = 1,
    exportSchema = false

)

abstract class TareaDb():RoomDatabase(){
    abstract fun tareaDo():TareaDao
}