package edu.neo.tpfinal.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.neo.tpfinal.R

class spash : AppCompatActivity() {

    private val PREFERENCE_FILE_KEY:String = "tpfinal.view"
    private val ESTADO_SESION:String = "estado.sesion.activo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)
        val sharePref: SharedPreferences = getSharedPreferences(
            PREFERENCE_FILE_KEY,
            MODE_PRIVATE
        )

        Handler().postDelayed(
            {

            //si esta recordar sessioon pasar directo a main, sino a loggin
            if (sharePref.getBoolean(ESTADO_SESION,false)){
                startActivity(Intent(this,MainActivity::class.java))
            }else{
                startActivity(Intent(this,LoginActivity::class.java))
            }
            this.finish()

        },2000)
    }
}