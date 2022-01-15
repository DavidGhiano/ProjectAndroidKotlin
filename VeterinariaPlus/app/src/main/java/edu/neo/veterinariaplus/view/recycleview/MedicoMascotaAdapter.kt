package edu.neo.veterinariaplus.view.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.model.Mascota

class MedicoMascotaAdapter(val lista: ArrayList<Mascota>) : RecyclerView.Adapter<MedicoMascotaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicoMascotaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medicomascota_layout, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MedicoMascotaAdapter.ViewHolder, position: Int) {
        holder.nombre.text = "Nombre: ${lista[position].nombre}"
        holder.tipo.text = "Tipo: ${lista[position].tipo}"
        holder.estado.text = "Estado: ${lista[position].estado}"
        holder.fecha.text = "Fecha: ${lista[position].fecha}"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var nombre:TextView
        var tipo: TextView
        var estado: TextView
        var fecha: TextView

        init {
            nombre = view.findViewById(R.id.dm_nombre)
            tipo = view.findViewById(R.id.dm_tipo)
            estado = view.findViewById(R.id.dm_estado)
            fecha = view.findViewById(R.id.dm_fecha)
        }
    }
}