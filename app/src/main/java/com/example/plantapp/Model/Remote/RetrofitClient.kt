package com.example.plantapp.Model.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {


    companion object{

        private const val BASE_URL ="https://my-json-server.typicode.com/"

        lateinit var  retrofitInstance : Retrofit

        fun retrofitInstance(): PlantApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(PlantApi::class.java)
        }

    }
}