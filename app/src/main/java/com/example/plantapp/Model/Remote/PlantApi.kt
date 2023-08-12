package com.example.plantapp.Model.Remote

import com.example.plantapp.Model.Remote.FromInternet.PlantDetail
import com.example.plantapp.Model.Remote.FromInternet.Plants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantApi {

    @GET("mauricioponce/TDApi/plantas") //hace la solicitud y trae la respuesta
    suspend fun fetchPlantList(): Response<List<Plants>>

    @GET("mauricioponce/TDApi/plantas/{id}")
    suspend fun fetchPlantDetail(@Path("id") id:String) : Response<PlantDetail>

}