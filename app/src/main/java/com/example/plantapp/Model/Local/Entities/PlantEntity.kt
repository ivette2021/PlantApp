package com.example.plantapp.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


//convertimos en una dataclass
@Entity(tableName="plant_list_table")
data class PlantEntity(
//colocamos los parametros de la api courses
@PrimaryKey
val id : String,
val nombre: String,
val tipo: String,
val imagen: String,
val descripcion: String


)
