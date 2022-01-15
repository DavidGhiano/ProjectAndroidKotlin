package edu.neo.veterinariaplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.veterinariaplus.dao.DbHelper
import java.time.LocalDate
import java.time.LocalDate.now

class MainViewModel : ViewModel(){
    fun contarCitas(context:Context):Int{
        val db:DbHelper = DbHelper(context,null)
        val hoy:String = LocalDate.now().toString().replace("-","")
        return db.contarCitas(hoy)
    }
}