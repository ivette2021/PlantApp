package com.example.plantapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.marsapp.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    private var binding: FragmentThirdBinding? = null
    private val mViewModel : PlantViewModel by activityViewModels()
    private var courseId : String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnAtras?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val bundle = arguments
        if (bundle != null) {
            val url = bundle.getString("url")
            val datos = bundle.getString("datos")
            val precio = bundle.getString("precio")
            val pDescription = bundle.getString("pDescription")
            // Cargar la imagen usando Glide o Picasso, se uso into para pasar directamente las ref. a imageview como parametro
            binding?.ivPlantaSeleccionada?.let {
                Glide.with(requireContext())
                    .load(url)
                    .into(it)
            }

            // Mostrar los datos adicionales
            binding?.tvNombreArticulo?.text = datos
            binding?.tvPrecioBig?.text = precio
            binding?.tvDescripcion?.text = pDescription
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

}