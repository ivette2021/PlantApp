package com.example.plantapp.Model.Local.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantapp.Model.Local.Entities.PlantDetailEntity
import com.example.plantapp.Model.Local.Entities.PlantEntity
import com.example.plantapp.Model.Local.PlantDao

@Database(entities = [PlantEntity:: class, PlantDetailEntity::class], version = 1, //colocamos las dos entidades que definimos anteriormente
    exportSchema = false)
abstract class PlantDataBase: RoomDatabase() {

    // referencia del dao
    abstract fun getPlantDao(): PlantDao

    companion object{

        @Volatile
        private var
                INSTANCE : PlantDataBase? = null
        fun getDataBase(context: Context) : PlantDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDataBase::class.java, "Plant_App")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }



}