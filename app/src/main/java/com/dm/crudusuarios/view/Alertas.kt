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
import com.airbnb.lottie.LottieAnimationView
import com.dm.crudusuarios.R

object Alertas {
    fun mostrarAlertaError(context: Context?, text: String?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_error, null)
        val textView = dialogView.findViewById<TextView>(R.id.tvMensajeError)
        val lottie = dialogView.findViewById<LottieAnimationView>(R.id.lottieError)
        if(!text.isNullOrEmpty()) textView.setText(text)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        lottie.apply {
            setAnimation("error.json")
            repeatCount = 0
            playAnimation()
        }

        dialogView.findViewById<Button>(R.id.btnTryAgain).setOnClickListener { v: View? -> dialog.dismiss() }

        dialog.show()
    }

    fun mostrarAlertaWarnign(context: Context?, text: String?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialogo_warning, null)
        val textView = dialogView.findViewById<TextView>(R.id.tvMensajeWarning)
        val lottie = dialogView.findViewById<LottieAnimationView>(R.id.lottieWarning)
        if(!text.isNullOrEmpty()) textView.setText(text)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        lottie.apply {
            setAnimation("warning.json")
            repeatCount = 0
            playAnimation()
        }

        dialogView.findViewById<Button>(R.id.btnTryAgain).setOnClickListener { v: View? -> dialog.dismiss() }

        dialog.show()
    }

    fun mostrarAlertaExito(context: Context?, text: String?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialogo_exito, null)
        val textView = dialogView.findViewById<TextView>(R.id.tvMensajeExito)
        val lottie = dialogView.findViewById<LottieAnimationView>(R.id.lottieExito)

        if(!text.isNullOrEmpty()) textView.setText(text)

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        lottie.apply {
            setAnimation("success.json")
            repeatCount = 0
            playAnimation()
        }

        dialogView.findViewById<Button>(R.id.btnAceptarExito).setOnClickListener {
            context?.startActivity(Intent(context, HomeActivity::class.java))

            dialog.dismiss()
        }

        dialog.show()
    }
}