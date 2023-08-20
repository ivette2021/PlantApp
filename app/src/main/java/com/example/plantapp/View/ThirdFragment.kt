package com.example.plantapp.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.plantApp.databinding.FragmentThirdBinding
import com.example.plantapp.PlantAdapter
import com.example.plantapp.ViewModel.PlantViewModel



class ThirdFragment : Fragment() {
    private lateinit var mBinding: FragmentThirdBinding

    // referencia para el adapter
    private val mViewModel : PlantViewModel by activityViewModels()
    private var plantId : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentThirdBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.btnAtras?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        arguments?.let { bundle ->
            
            plantId = bundle.getString("plantId")
            Log.d("seleccion", plantId.toString())
        }

        plantId?.let { id ->
            mViewModel.getPlantDetailByIdFromInternet(id)

        }

        mViewModel.getPlantDetail().observe(viewLifecycleOwner, Observer {
            Log.d("seleccion3", plantId.toString())


// cargamos los datos desde la seleccion
            Glide.with(mBinding.ivPlantaSeleccionada).load(it.imagen).into(mBinding.ivPlantaSeleccionada)
            mBinding.tvNombrePlanta.text = it.nombre
            mBinding.tvRequerimientoAgua.text= it.tipo
            mBinding.tvDescripcion.text= it.descripcion

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}