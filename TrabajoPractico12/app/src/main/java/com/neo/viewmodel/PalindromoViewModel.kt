package com.neo.viewmodel

import androidx.lifecycle.ViewModel
import com.neo.model.Palindromo

class PalindromoViewModel :ViewModel() {

    fun comprobar(texto:String):Boolean{
        var textoModificado:String =""
        texto.toLowerCase().forEach {
                when (it) {
                    'á' -> textoModificado = textoModificado + 'a'
                    'é' -> textoModificado = textoModificado + 'e'
                    'í' -> textoModificado = textoModificado + 'i'
                    'ó' -> textoModificado = textoModificado + 'o'
                    'ú' -> textoModificado = textoModificado + 'u'
                    'ü' -> textoModificado = textoModificado + 'u'
                    else -> if(it.toInt()>=97 && it.toInt()<=122) textoModificado = textoModificado + it
            }
        }
        var cantidad:Int = textoModificado.length-1
        for(i in 0..cantidad){
            if(textoModificado[i]!=textoModificado[cantidad-i]) return false
        }
        return true
    }
}