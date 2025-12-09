package com.dm.crudusuarios.view

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dm.crudusuarios.R
import com.dm.crudusuarios.databinding.ActivityUpdateUserBinding
import com.dm.crudusuarios.model.UsuarioModel
import com.dm.crudusuarios.utils.parcelable

class UpdateUser : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateUserBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: UsuarioModel? = intent.parcelable("usuario")
        initUi(user)
    }

    private fun initUi(user: UsuarioModel?) {
        binding.etNombre.setText(user?.usu_nombre ?: "")
        binding.etPrimerApellido.setText(user?.usu_papellido ?: "")
        binding.etSegundoApellido.setText(user?.usu_sapellido ?: "")
        binding.etDireccion.setText(user?.usu_direccion ?: "")
        binding.etTelefono.setText(user?.usu_telefono ?: "")
        binding.etCorreo.setText(user?.usu_correo ?: "")

        val opcionesGenero = listOf("M", "F")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, opcionesGenero)
        binding.etGenero.setAdapter(adapter)
        binding.etGenero.setOnClickListener {
            binding.etGenero.showDropDown()
        }

        binding.btnGuardarEditar.setOnClickListener {
            //Alertas.mostrarAlertaError(this, getString(R.string.ups_hubo_un_error_al_enviar_el_registro_nintenta_nuevamente_mas_tarde))
            Alertas.mostrarAlertaExito(this, null)
        }
    }
}