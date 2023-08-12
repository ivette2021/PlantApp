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

class PlantAdapter :RecyclerView.Adapter<PlantAdapter.PlantVH>(){
    // referencia para el adapter
    private var listPlants= listOf<PlantEntity>()
    private  val selectedPlant= MutableLiveData<PlantEntity>()

    fun update (list:List<PlantEntity>){
        listPlants= list
        notifyDataSetChanged()
    }

    // función para seleccionar
    fun selectedPlant(): LiveData<PlantEntity> = selectedPlant

    inner class PlantVH(private  val mbinding : PlantsListBinding):
        RecyclerView.ViewHolder(mbinding.root), View.OnClickListener{

        fun bind(course: PlantEntity){

            Glide.with(mbinding.iv_item).load(course.imagen).centerCrop().into(mbinding.iv_item)
            mbinding.tvname.text= course.title
            mbinding.tvdescription.text= course.previewDescription
            mbinding.tvduration.text= "duración:"+ course.weeks.toString()+"Semanas"
            mbinding.tvstart.text= "Inicio:" + course.star
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View) {
            // referencia a la selección
            selectedPlant.value= listPlants[adapterPosition]
            Log.d("ONCLICK",adapterPosition.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVH {
        return CourseVH(CoursesListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listPlants.size


    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        val course= listPlants[position]
        holder.bind(course)
    }


}