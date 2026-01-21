package com.dm.crudusuarios.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dm.crudusuarios.R

class IconsAdapter(
    private val icons: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<IconsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_icons, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = icons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = icons[position]

        val resId = holder.itemView.context.resources.getIdentifier(
            name,
            "drawable",
            holder.itemView.context.packageName
        )

        holder.img.setImageResource(resId)

        holder.itemView.setOnClickListener {
            onClick(name)
        }
    }
}
