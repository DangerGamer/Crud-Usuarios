package com.dm.crudusuarios.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dm.crudusuarios.R
import com.dm.crudusuarios.model.UsuarioModel
import com.dm.crudusuarios.view.UpdateUser

class UserAdapter(
    private val context: Context,
    private var users: List<UsuarioModel>
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val logosM = listOf(
        R.drawable.h_1,
        R.drawable.h_2,
        R.drawable.h_3,
        R.drawable.h_4,
        R.drawable.h_5,
        R.drawable.h_6
    )

    private val logosF = listOf(
        R.drawable.m_1,
        R.drawable.m_2,
        R.drawable.m_3,
        R.drawable.m_4,
        R.drawable.m_5,
        R.drawable.m_6
    )

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivUser: ImageView = view.findViewById(R.id.ivUser)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val tvTelefono: TextView = view.findViewById(R.id.tvTelefono)
        val tvDireccion: TextView = view.findViewById(R.id.tvDireccion)
        val ivEditar: ImageView = view.findViewById(R.id.ivEditar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        val randomLogo = if (user.usu_genero == "M") logosM.random() else logosF.random()
        holder.ivUser.setImageResource(randomLogo)
        holder.tvName.text = """${user.usu_nombre} ${user.usu_papellido} ${user.usu_sapellido}"""
        holder.tvEmail.text = user.usu_correo
        holder.tvTelefono.text = user.usu_telefono
        holder.tvDireccion.text = user.usu_direccion

        holder.ivEditar.setOnClickListener {
            val intent = Intent(context, UpdateUser::class.java)
            intent.putExtra("usuario", users[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = users.size

    fun updateData(newUsers: List<UsuarioModel>) {
        users = newUsers
        notifyDataSetChanged()
    }
}
