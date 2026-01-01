package com.dm.crudusuarios.view.alerts

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getString
import com.dm.crudusuarios.R

class AlertaError(
    private val context: Context,
    private val texto: String?,
    private val onAceptar: () -> Unit
) {
    fun mostrar() {
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_error, null)

        dialogView.findViewById<TextView>(R.id.tvMensajeError).text = if (texto.isNullOrEmpty()) getString(context, R.string.operacion_ejecutada_correctamente) else texto

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btnTryAgain).setOnClickListener {
            onAceptar()
            dialog.dismiss()
        }

        dialog.show()
    }
}