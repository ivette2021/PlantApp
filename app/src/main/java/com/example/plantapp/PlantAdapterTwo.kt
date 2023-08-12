package com.example.plantapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsapp.databinding.PlantsListBinding
import com.example.plantapp.Model.Local.Entities.PlantEntity

class PlantAdapterTwo :RecyclerView.Adapter<PlantAdapterTwo.PlantVH>(){
    // referencia para el adapter
    private var listBinding2= listOf<PlantEntity>()
    private  val selectedPlant2= MutableLiveData<PlantEntity>()

    fun update (list:List<PlantEntity>){
        listBinding2= list
        notifyDataSetChanged()
    }

    // función para seleccionar
    fun selectedPlant2(): LiveData<PlantEntity> = selectedPlant2

    inner class PlantVH(private  val mbinding : PlantsListBinding):
        RecyclerView.ViewHolder(mbinding.root), View.OnClickListener{

        fun bind(plant: PlantEntity){ //los id de los xml con guion bajo se escriben aqui como camelcase

            Glide.with(mbinding.ivItem2).load(plant.imagen).centerCrop().into(mbinding.ivItem2)
            mbinding.tvNombrePlanta2.text= plant.nombre
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View) {
            // referencia a la selección
            selectedPlant2.value= listBinding2[adapterPosition]
            Log.d("ONCLICK",adapterPosition.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantVH {
        return PlantVH(PlantsListBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun getItemCount() = listBinding2.size

    override fun onBindViewHolder(holder: PlantAdapterTwo.PlantVH, position: Int) {
        val plant= listBinding2[position]
        holder.bind(plant)
    }

}