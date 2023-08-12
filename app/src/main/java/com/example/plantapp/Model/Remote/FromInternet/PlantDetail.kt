package com.example.plantapp.Model.Remote.FromInternet


//representa la entidad que viene de internet
data class PlantDetail(
    val id : String,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String
)