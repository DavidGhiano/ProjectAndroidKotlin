package edu.neo.veterinariaplus.model

import java.io.Serializable

data class Mascota(
    var id: Int = 0,
    val nombre: String,
    val tipo: String,
    val raza: String,
    val doctor: String,
    val edad: String,
    val fecha: String,
    val causaAtencion: String,
    var medicamento: String = "",
    var resultado: String = "",
    var estado:String = "En espera",
    var causa: String = ""
) : Serializable