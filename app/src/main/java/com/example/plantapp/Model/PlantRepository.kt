package com.example.plantapp.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.plantapp.Model.Local.Entities.PlantDetailEntity
import com.example.plantapp.Model.Local.PlantDao
import com.example.plantapp.Model.Remote.RetrofitClient

class PlantRepository( private val plantDao: PlantDao){

    // retrofit Cliente
    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
    val plantListLiveData = plantDao.getAllPlants()

    // un elemento
    val plantDetailLiveData= MutableLiveData<PlantDetailEntity>()


    suspend fun fetchPlant(){ //funcion asincrona , corrutina
        val service = kotlin.runCatching { networkService.fetchPlantList() } //Representa una instancia del servicio de red

        service.onSuccess {
            when (it.code()){
                in 200..299 ->it.body()?.let {// si el servicio responde con exito insertamos la lista de plantas

                    Log.d("Plantas",it.toString())


                    plantDao.insertAllPlants(fromInternetPlantEntity(it))

                }
                else-> Log.d("Repo","${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}") //si falla captamos el error
            }
        }
    }

    suspend fun fetchPlantDetail(id: String): PlantDetailEntity?{ //insertar plantas por id
        val service = kotlin.runCatching { networkService.fetchPlantDetail(id) }
        return service.getOrNull()?.body()?.let { plantDetail -> //retornamos el servicio, detalle de las plantas
            // guardp los datos que viene del mapper y luego se los paso a dao directo
            val plantDetailEntity = fromInternetPlantDetailEntity(plantDetail)
            //inserto los detalles de cada planta  del repositorio
            plantDao.insertPlantDetail(plantDetailEntity)
            plantDetailEntity
        }
    }

}