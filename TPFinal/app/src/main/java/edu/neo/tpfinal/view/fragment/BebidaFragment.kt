package edu.neo.tpfinal.view.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Bebida
import edu.neo.tpfinal.viewmodel.BebidaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BebidaFragment : Fragment() {

    private lateinit var bebidaViewModel: BebidaViewModel
    private lateinit var image:ImageView
    private lateinit var nombre:TextView
    private lateinit var categoria:TextView
    private lateinit var instruccion:TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bebida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Inicializar(view)
        val bebidaVM = ViewModelProvider(this).get(BebidaViewModel::class.java)

        bebidaVM.getBebida()
            .enqueue(object : Callback<Bebida> {
                override fun onResponse(call: Call<Bebida>, response: Response<Bebida>) {
                    if (response.body() != null) {
                        val data = response.body()
                        nombre.setText(data?.drinks?.get(0)?.strDrink)
                        categoria.setText("(${data?.drinks?.get(0)?.strCategory})")
                        instruccion.setText("Instruccion: ${data?.drinks?.get(0)?.strInstructions}")
                        Glide
                            .with(view.context)
                            .load(data?.drinks?.get(0)?.strDrinkThumb)
                            .centerInside()
                            .into(image)
                    }
                }

                override fun onFailure(call: Call<Bebida>, t: Throwable) {
                    Log.e("Error-Invoke-API", t.message.toString())
                }

            })
    }

    fun Inicializar(view:View){
        image = view.findViewById(R.id.beb_imagen)
        nombre = view.findViewById(R.id.beb_nombre)
        categoria = view.findViewById(R.id.beb_categoria)
        instruccion = view.findViewById(R.id.beb_instruccion)
    }
}