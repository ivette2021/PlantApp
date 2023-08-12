package com.example.plantapp.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantapp.Model.Local.Entities.PlantDetailEntity
import com.example.plantapp.Model.Local.Entities.PlantEntity


@Dao
interface PlantDao {

    // insertar lista de plantas
    @Insert(onConflict = OnConflictStrategy.REPLACE)//si hay duplicado , que simplemente reemplace
    //funciones asincronicas
    suspend fun insertAllPlants(listPlants: List<PlantEntity>)


    // seleccionar Listado de los plantas

    @Query("SELECT * FROM plant_list_table ORDER BY id ASC")
    fun getAllPlants(): LiveData<List<PlantEntity>>

    // inserta 1 item planta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlantDetail(course: PlantDetailEntity)

    @Query("SELECT * FROM plant_detail_table WHERE id=:id")
    fun getPlantDetailById(id: String): LiveData<PlantDetailEntity>

}
