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
import com.example.plantApp.databinding.FragmentFirstBinding
import com.example.plantapp.PlantAdapter
import com.example.plantapp.ViewModel.PlantViewModel


class FirstFragment : Fragment() {

    private lateinit var  mBinding : FragmentFirstBinding
    private val mViewModel : PlantViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // DEBEMOS INTANCIAR ADAPTER

        val adapter = PlantAdapter()
        mBinding.Rv.adapter= adapter
        mBinding.Rv.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mViewModel.getPlantList().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }

        })


        // MÉTODO PARA SELECCIONAR

        adapter.selectedPlant().observe(viewLifecycleOwner, Observer {
            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion", it.id.toString())

            }
            val bundle = Bundle().apply {
                putString("plantId", it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment, bundle)

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}