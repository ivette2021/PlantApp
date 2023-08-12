package com.example.plantapp.Model.Local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="plant_detail_table")
class PlantDetailEntity (
//datos cuando consultamos un elemento de la lista de la api
@PrimaryKey
val id : String,
val nombre: String,
val tipo: String,
val imagen: String,
val descripcion: String

)