package com.dm.crudusuarios.view.alerts

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.dm.crudusuarios.R

class AlertaError(
    private val context: Context,
    private val onAceptar: () -> Unit
) {
    fun mostrar() {
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_error, null)

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