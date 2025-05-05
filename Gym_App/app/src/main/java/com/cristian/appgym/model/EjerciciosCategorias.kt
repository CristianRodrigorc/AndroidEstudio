package com.cristian.appgym.model

data class EjerciciosCategorias(
    val biceps: List<Ejercicio>,
    val triceps: List<Ejercicio>,
    val pecho: List<Ejercicio>,
    val espalda: List<Ejercicio>,
    val piernas: List<Ejercicio>,
    val hombros: List<Ejercicio>,
    val abdomen: List<Ejercicio>
)
