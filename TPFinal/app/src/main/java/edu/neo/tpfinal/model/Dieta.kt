package edu.neo.tpfinal.model

import java.io.Serializable

data class Dieta(
    val tipo_dieta: String,
    val principal: String,
    val secundario: String,
    val bebida:String,
    val postre:String,
    val otro:String?,
    val hambre:String,
    val fecha:String,
    val hora:String
):Serializable