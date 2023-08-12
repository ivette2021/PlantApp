package com.example.plantapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantapp.Model.Local.DataBase.PlantDataBase
import com.example.plantapp.Model.Local.Entities.PlantDetailEntity
import com.example.plantapp.Model.Local.Entities.PlantEntity
import com.example.plantapp.Model.PlantRepository
import kotlinx.coroutines.launch

class PlantViewModel(application: Application) :AndroidViewModel(application) { //se agrega el contructor viewmodel de android

    // conexión con el repositorio
    private val repository : PlantRepository

    // referenciar las entidades primero referenciamos la lista de los cursos **
    private val plantDetailLiveData = MutableLiveData<PlantDetailEntity>()

    // para seleccionar de una pantalla a otra
    private var idSelected : String="-1"

    init{
        // tiene la instancia de la base de datos, el dao, entregamos estas instancias al repositorio
        val bd= PlantDataBase.getDataBase(application)
        val plantDao= bd.getPlantDao()

        repository = PlantRepository(plantDao)

        // llamo al método del repository

        viewModelScope.launch { //trabajamos en el hilo secundario
            repository.fetchPlant()
        }
    }

    // funcion listado de los elementos
    fun getPlantList(): LiveData<List<PlantEntity>> = repository.plantListLiveData //referencia al repositorio

    // para obtener un elemento por id desde lo que se selecciono
    fun getPlantDetail(): LiveData<PlantDetailEntity> = plantDetailLiveData

    // desde el segundo fragmento le paso la seleccion
    fun getPlantDetailByIdFromInternet(id: String)= viewModelScope.launch {//

        val plantDetail = repository.fetchPlantDetail(id)
        plantDetail?.let {

            plantDetailLiveData.postValue(it) //lo que seleccione se lo pasara al repositorio
        }
    }
}