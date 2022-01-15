package edu.neo.tpfinal.model

import java.io.Serializable

data class Persona(
    val id: String?,
    val nombre: String,
    val apellido: String,
    val dni: Int,
    val sexo: String,
    val fecNac: String,
    val localidad: String,
    val tratamiento: String,
    val usuario:User,
    val dieta:Dieta?
    ):Serializable

