package edu.neo.tpfinal.view

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.renderscript.Sampler
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.User
import edu.neo.tpfinal.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val PREFERENCE_FILE_KEY: String = "tpfinal.view"
    private val ESTADO_SESION: String = "estado.sesion.activo"
    private val ESTADO_USUARIO: String = "estado.usuario.activo"
    private lateinit var firebaseDB: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val user: TextInputEditText = findViewById(R.id.log_user)
        val pass: TextInputEditText = findViewById(R.id.log_pass)
        val user_field: TextInputLayout = findViewById(R.id.log_user_field)
        val pass_field: TextInputLayout = findViewById(R.id.log_pass_field)
        val recordar: Switch = findViewById(R.id.log_switch)
        val ingresar: Button = findViewById(R.id.log_iniciar)
        val registrar: Button = findViewById(R.id.log_registrar)


        ingresar.setOnClickListener {

            if (!user.text.toString().equals("")) {
                val intent: Intent = Intent(this, MainActivity::class.java)
                InicializarDB()
                dbReference.child("Persona").child(user.text.toString()).addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                val password =
                                    snapshot.child("usuario").child("password").getValue()
                                        .toString()
                                if (password.equals(pass.text.toString())) {
                                    guardarEstado(recordar.isChecked, user.text.toString())
                                    startActivity(intent)
                                } else {
                                    user_field.error = "usuario y/o contraseña incorrecta"
                                    pass_field.error = ""
                                    Handler().postDelayed({
                                        user_field.error = null
                                        pass_field.error = null
                                    }, 1500)
                                }
                            }else{
                                user_field.error = "usuario y/o contraseña incorrecta"
                                pass_field.error = ""
                                Handler().postDelayed({
                                    user_field.error = null
                                    pass_field.error = null
                                }, 1500)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    }
                )
            }
        }
        registrar.setOnClickListener {
            startActivity(Intent(this, RegistrarActivity::class.java))
        }


        //TODO: si existe usuario y contraseña está bien pasar a main, sino dialogo
    }

    fun InicializarDB() {
        FirebaseApp.initializeApp(this)
        firebaseDB = FirebaseDatabase.getInstance()
        dbReference = firebaseDB.getReference()
    }

    fun guardarEstado(valor: Boolean, userID: String) {
        val sharePref: SharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE)
        with(sharePref.edit()) {
            putBoolean(ESTADO_SESION, valor)
            putString(ESTADO_USUARIO, userID)
            commit()
        }
    }

    fun obtenerEstado(): String {
        val sharePref: SharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE)
        return sharePref.getString(ESTADO_USUARIO, "").toString()
    }


}