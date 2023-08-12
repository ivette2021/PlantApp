package com.example.plantapp


import com.example.plantapp.Model.Local.Entities.PlantEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestPlantEntity {


    private lateinit var plantEntity: PlantEntity

    @Before
    fun setup() {

        plantEntity = PlantEntity(
            id = "3",
            nombre = "Hola Mundo",
            tipo = "65748",
            imagen = "imagen",
            descripcion = "flor blah, blah, blah",

            )






    }
    @After
    fun tearDown() {

        // acciones de limpieza y liberación de recursos
    }

    @Test

    fun testPlantConstructor() {
        // verificar que los valores asignados en el constructor sean correctos

        assert(plantEntity.id == "3")
        assert(plantEntity.nombre == "Hola Mundo")
        assert(plantEntity.tipo == "65748")
        assert(plantEntity.imagen == "imagen")
        assert(plantEntity.descripcion == "flor blah, blah, blah")


    }
    }