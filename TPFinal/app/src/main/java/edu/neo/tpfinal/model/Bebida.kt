package edu.neo.tpfinal.model

import java.io.Serializable


data class Bebida(
    val drinks:ArrayList<Drinks>

):Serializable

data class Drinks(
    val strDrink:String,
    val strCategory:String,
    val strInstructions:String,
    val strDrinkThumb:String
):Serializable