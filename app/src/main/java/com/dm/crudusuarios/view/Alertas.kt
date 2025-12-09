package com.dm.crudusuarios.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.dm.crudusuarios.R

object Alertas {
    fun mostrarAlertaError(context: Context?, text: String?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_error, null)
        val textView = dialogView.findViewById<TextView>(R.id.tvMensajeError)
        if(!text.isNullOrEmpty()) textView.setText(text)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnTryAgain = dialogView.findViewById<Button>(R.id.btnTryAgain)
        btnTryAgain.setOnClickListener { v: View? -> dialog.dismiss() }

        dialog.show()
    }

    fun mostrarAlertaExito(context: Context?, text: String?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialogo_exito, null)
        val textView = dialogView.findViewById<TextView>(R.id.tvMensajeExito)
        if(!text.isNullOrEmpty()) textView.setText(text)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnAceptarExito = dialogView.findViewById<Button>(R.id.btnAceptarExito)
        btnAceptarExito.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            context?.startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }
}