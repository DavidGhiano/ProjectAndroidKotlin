package com.neo.viewmodel

import android.text.Editable
import androidx.lifecycle.ViewModel

class TemperaturaViewModel :ViewModel() {

    fun celToFah(c:String) : String{
        return ((c.toDouble() * 1.8) + 32).toString()
    }
    fun fahToCel(f:String) : String{
        return ((f.toDouble() -32)/1.8).toString()
    }
}