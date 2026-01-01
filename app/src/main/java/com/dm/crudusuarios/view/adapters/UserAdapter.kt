package com.dm.crudusuarios.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dm.crudusuarios.R
import com.dm.crudusuarios.model.UsuarioModel
import com.dm.crudusuarios.view.AdministrarUsuario

class UserAdapter(
    private val context: Context,
    private var users: List<UsuarioModel>,
    private val onSelectionChanged: (Int) -> Unit
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

    private val selectedIds = mutableSetOf<Int>()
    private var selectionMode = false

    fun getSelectedUsers(): List<UsuarioModel> {
        return users.filter { selectedIds.contains(it.usu_id) }
    }

    fun removeUsers(toRemove: List<UsuarioModel>) {
        val idsToRemove = toRemove.map { it.usu_id }.toSet()

        users = users.filterNot { idsToRemove.contains(it.usu_id) }

        // 🔥 LIMPIEZA REAL
        selectedIds.removeAll(idsToRemove)

        if (selectedIds.isEmpty()) {
            selectionMode = false
        }

        notifyDataSetChanged()
        onSelectionChanged(selectedIds.size)
    }

    private fun toggleSelection(user: UsuarioModel) {
        if (selectedIds.contains(user.usu_id)) {
            selectedIds.remove(user.usu_id)
            if (selectedIds.isEmpty()) {
                selectionMode = false
            }
        } else {
            selectedIds.add(user.usu_id)
            selectionMode = true
        }

        notifyDataSetChanged()
        onSelectionChanged(selectedIds.size)
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivUser: ImageView = view.findViewById(R.id.ivUser)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val tvTelefono: TextView = view.findViewById(R.id.tvTelefono)
        val tvDireccion: TextView = view.findViewById(R.id.tvDireccion)
        val ivEditar: ImageView = view.findViewById(R.id.ivEditar)

        val defaultPrimaryColor = tvName.currentTextColor
        val defaultSecondaryColor = tvEmail.currentTextColor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        val isSelected = selectedIds.contains(user.usu_id)
        holder.itemView.isSelected = isSelected

        if (isSelected) {
            holder.tvName.setTextColor(context.getColor(android.R.color.white))
            holder.tvEmail.setTextColor(context.getColor(android.R.color.white))
            holder.tvTelefono.setTextColor(context.getColor(android.R.color.white))
            holder.tvDireccion.setTextColor(context.getColor(android.R.color.white))
        } else {
            holder.tvName.setTextColor(holder.defaultPrimaryColor)
            holder.tvEmail.setTextColor(holder.defaultSecondaryColor)
            holder.tvTelefono.setTextColor(holder.defaultSecondaryColor)
            holder.tvDireccion.setTextColor(holder.defaultSecondaryColor)
        }

        val randomLogo = if (user.usu_genero == "M") logosM.random() else logosF.random()
        holder.ivUser.setImageResource(randomLogo)
        holder.tvName.text = """${user.usu_nombre} ${user.usu_papellido} ${user.usu_sapellido}"""
        holder.tvEmail.text = user.usu_correo
        holder.tvTelefono.text = user.usu_telefono
        holder.tvDireccion.text = user.usu_direccion

        holder.itemView.isSelected = selectedIds.contains(user.usu_id)

        holder.itemView.setOnLongClickListener {
            selectionMode = true
            toggleSelection(user)
            true
        }

        holder.itemView.setOnClickListener {
            if (selectionMode) {
                toggleSelection(user)
            }
        }

        holder.ivEditar.setOnClickListener {
            if (selectionMode) return@setOnClickListener

            val intent = Intent(context, AdministrarUsuario::class.java)
            intent.putExtra("id_usuario", user.usu_id)
            intent.putExtra("crud", "editar")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = users.size

    fun updateData(newUsers: List<UsuarioModel>) {
        users = newUsers
        notifyDataSetChanged()
    }
}
