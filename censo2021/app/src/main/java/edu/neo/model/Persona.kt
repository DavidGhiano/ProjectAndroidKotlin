package edu.neo.model

import java.io.Serializable

data class Persona(
    val id:Int?,
    val tipoDoc:String,
    val numDoc:Int,
    val nombre:String,
    val apellido:String,
    val fecNac:String,
    val sexo:String,
    val direccion:String,
    val telefono:String,
    val ocupacion:String,
    val ingreso:String
):Serializable {
}