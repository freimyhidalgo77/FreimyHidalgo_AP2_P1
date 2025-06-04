package edu.ucne.freimyhidalgo_p1_ap2.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.freimyhidalgo_p1_ap2.data.local.database.TareaDb
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton

    fun TareaProvideDb(@ApplicationContext applicationContext: Context): TareaDb =
        Room.databaseBuilder(
            applicationContext,
            TareaDb::class.java,
            "TareaDb"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton

    fun TareaProvideDao(tareaDBO:TareaDb) = tareaDBO.tareaDo()

}





