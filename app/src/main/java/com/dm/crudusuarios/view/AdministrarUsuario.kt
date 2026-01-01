package com.dm.crudusuarios.view

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dm.crudusuarios.R
import com.dm.crudusuarios.databinding.ActivityAdministracionUsuarioBinding
import com.dm.crudusuarios.model.UsuarioModel
import com.dm.crudusuarios.view.alerts.AlertaError
import com.dm.crudusuarios.view.alerts.AlertaExito
import com.dm.crudusuarios.viewmodel.AdministrarUsuarioViewModel

class AdministrarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityAdministracionUsuarioBinding
    private lateinit var viewModel: AdministrarUsuarioViewModel
    private var genero = ""
    private var id_usuario = 0
    private var crud = ""

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdministracionUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id_usuario = intent.getIntExtra("id_usuario", -1)
        crud = intent.getStringExtra("crud").toString()

        initUi()
    }

    private fun initUi() {
        binding.tvTitulo.text =
            if (crud.equals("crear")) "Crear usuario" else "Editar datos personales"

        viewModel = ViewModelProvider(this)[AdministrarUsuarioViewModel::class.java]

        viewModel.user.observe(this) { user ->
            if (user == null) {
                AlertaError(this, getString(R.string.usuario_no_encontrado)){
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            } else {
                mostrarDatos(user)
            }
        }

        viewModel.error.observe(this) { error ->
            if (!error.isNullOrEmpty()) {
                if (crud.equals("crear")) {
                    AlertaError(this, getString(R.string.ups_no_se_cre_el_usuario_intenta_nuevamente)){
                        setResult(Activity.RESULT_OK)
                        finish()
                    }.mostrar()
                } else {
                    AlertaError(this, getString(R.string.ups_no_se_actualizo_el_usuario_intenta_nuevamente)){
                        setResult(Activity.RESULT_OK)
                        finish()
                    }.mostrar()
                }

            }
        }

        viewModel.updated.observe(this) { updated ->
            if (updated) {
                AlertaExito(this, getString(R.string.registro_actualizado)) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }.mostrar()
            }
        }

        if (crud.equals("editar")) {
            binding.etGenero.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
                if (focus) {
                    val opcionesGenero = listOf("Masculino", "Femenino")
                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, opcionesGenero)
                    binding.etGenero.setAdapter(adapter)
                    binding.etGenero.setOnClickListener {
                        binding.etGenero.showDropDown()
                    }
                } else {
                    val generoTexto = when (genero) {
                        "M" -> "Masculino"
                        "F" -> "Femenino"
                        else -> ""
                    }
                    binding.etGenero.setText(generoTexto, false)
                }
            }

            viewModel.fetchUsersById(id_usuario)
        }


        viewModel.created.observe(this) { created ->
            if (created) {
                AlertaExito(this, getString(R.string.usuario_creado)){
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }


        val opcionesGenero = listOf("Masculino", "Femenino")
        val generoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            opcionesGenero
        )
        binding.etGenero.setAdapter(generoAdapter)
        binding.etGenero.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etGenero.windowToken, 0)
            binding.etGenero.showDropDown()
        }

        binding.btnGuardarEditar.setOnClickListener {
            val generoSeleccionado = when (binding.etGenero.text.toString()) {
                "Masculino" -> "M"
                "Femenino" -> "F"
                else -> ""
            }

            val usuarioModel = UsuarioModel(
                id_usuario,
                binding.etNombre.text.toString(),
                binding.etPrimerApellido.text.toString(),
                binding.etSegundoApellido.text.toString(),
                binding.etDireccion.text.toString(),
                binding.etTelefono.text.toString(),
                binding.etCorreo.text.toString(),
                generoSeleccionado
            )

            if (crud.equals("crear")) {
                viewModel.createUser(usuarioModel)
            } else {
                viewModel.updateUser(usuarioModel)
            }
        }
    }

    private fun mostrarDatos(user: UsuarioModel) {
        binding.etNombre.setText(user.usu_nombre)
        binding.etPrimerApellido.setText(user.usu_papellido)
        binding.etSegundoApellido.setText(user.usu_sapellido)
        binding.etDireccion.setText(user.usu_direccion)
        binding.etTelefono.setText(user.usu_telefono)
        binding.etCorreo.setText(user.usu_correo)

        genero = user.usu_genero
        val generoTexto = when (user.usu_genero) {
            "M" -> "Masculino"
            "F" -> "Femenino"
            else -> ""
        }
        binding.etGenero.setText(generoTexto, false)
    }
}