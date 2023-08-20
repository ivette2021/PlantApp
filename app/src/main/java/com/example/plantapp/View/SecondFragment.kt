package com.example.plantapp.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantApp.R
import com.example.plantApp.databinding.FragmentSecondBinding
import com.example.plantapp.PlantAdapter
import com.example.plantapp.PlantAdapterTwo
import com.example.plantapp.ViewModel.PlantViewModel


class SecondFragment : Fragment() {

    private lateinit var  mBinding : FragmentSecondBinding
    private val mViewModel : PlantViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // DEBEMOS INTANCIAR ADAPTER

        val adapter = PlantAdapterTwo()
        mBinding.Rv2.adapter= adapter
        mBinding.Rv2.layoutManager= GridLayoutManager(context, 2)
        mViewModel.getPlantList().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }
        })
        
        // MÉTODO PARA SELECCIONAR

        adapter.selectedPlant2().observe(viewLifecycleOwner, Observer {
            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion 2", it.id.toString())

            }
            val bundle = Bundle().apply {
                putString("plantId", it.id)
            }
            findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment, bundle)

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}