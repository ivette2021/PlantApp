package com.example.plantapp.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.plantApp.R
import com.example.plantApp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView



    class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

        private lateinit var binding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            val bottomNavigationView: BottomNavigationView = findViewById(R.id.NavigationView)
            bottomNavigationView.setOnNavigationItemSelectedListener(this)

            // ACCION DE ENVIAR CORREO Eléctronico
            binding.fab.setOnClickListener {
                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type = "text/plain"
                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("admisión@centrofuturo.cl"))
                mintent.putExtra(Intent.EXTRA_SUBJECT, "Solicito información sobre este curso")
                mintent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n" +
                            "Quisiera pedir información sobre este curso,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."
                )
                try {
                    startActivity(mintent)
                } catch (e: Exception) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
            }

        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            Log.d("MiTag", "Mensaje de depuración")
            when (item.itemId) {
                R.id.MyGarden -> {
                    navigateToFragment(FirstFragment())
                    Log.d("MiTag", "Moovida a primer fragmento")
                    return true
                }
                R.id.Plant_list -> {

                    navigateToFragment(SecondFragment())
                    Log.d("mitag2","movida de 1 a 2")
                    return true
                }
            }
            return false
        }

        private fun navigateToFragment(fragment: Fragment) {
             // Elimina el fragmento anterior del backstack

            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .addToBackStack(null)
                .commit()

        }
    }