package com.example.plantapp.Model

import com.example.plantapp.Model.Local.Entities.PlantDetailEntity
import com.example.plantapp.Model.Local.Entities.PlantEntity
import com.example.plantapp.Model.Remote.FromInternet.PlantDetail
import com.example.plantapp.Model.Remote.FromInternet.Plants

fun fromInternetPlantEntity( plantList: List<Plants>) :List<PlantEntity>{

    return plantList.map {//aca lo que llegue de internet se lo pasaremos a la parte local
        PlantEntity( //hay que pasarle todos los datos a la local debido a que remotas y local deben tener todos los campos ocupados
            id=it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion
        )
    }
}

fun fromInternetPlantDetailEntity( plant: PlantDetail) : PlantDetailEntity {

    return PlantDetailEntity(

        id =plant.id,
        nombre = plant.nombre,
        tipo = plant.tipo,
        imagen = plant.imagen,
        descripcion = plant.descripcion
    )
}