package edu.neo.veterinariaplus.view.recycleview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.model.Mascota
import edu.neo.veterinariaplus.view.ModificarMascotaActivity


class MascotaAdapter(val lista: ArrayList<Mascota>) :
    RecyclerView.Adapter<MascotaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mascota_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MascotaAdapter.ViewHolder, position: Int) {
        holder.id.text = lista[position].id.toString()
        holder.nombre.text = lista[position].nombre
        holder.estado.text = lista[position].estado
        holder.tipo.text = lista[position].tipo
        holder.raza.text = lista[position].raza
        holder.edad.text = lista[position].edad
        holder.causas.text = lista[position].causaAtencion
        holder.modificar.setOnClickListener(View.OnClickListener {
            val intent:Intent = Intent(it.context,ModificarMascotaActivity::class.java)

            intent.putExtra("id", lista[position].id.toString())
            intent.putExtra("nombre",lista[position].nombre)
            intent.putExtra("causas",lista[position].causaAtencion)

            it.context.startActivity(intent)
        })

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var id: TextView
        var nombre: TextView
        var estado: TextView
        var tipo: TextView
        var raza: TextView
        var edad: TextView
        var causas: TextView
        var modificar: Button

        init {
            id = view.findViewById(R.id.m_l_id)
            nombre = view.findViewById(R.id.m_l_nombre)
            estado = view.findViewById(R.id.m_l_estado)
            tipo = view.findViewById(R.id.m_l_tipo)
            raza = view.findViewById(R.id.m_l_raza)
            edad = view.findViewById(R.id.m_l_edad)
            causas = view.findViewById(R.id.m_l_causas)
            modificar = view.findViewById(R.id.m_l_revisar)
        }
    }
}