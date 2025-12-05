package com.dm.crudusuarios.viewmodel

import RetrofitClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.crudusuarios.model.UsuarioModel
import kotlinx.coroutines.launch

class UsuarioViewModel: ViewModel() {

    private val _users = MutableLiveData<List<UsuarioModel>>()
    val users: LiveData<List<UsuarioModel>> = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getUsers()
                if (response.success) {
                    _users.value = response.data ?: emptyList()
                } else {
                    _error.value = response.error ?: "Error desconocido"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
                println("Error: ${e.message}")
            }
        }
    }

    fun addUser(usu_id: Int, usu_nombre: String, usu_papellido: String, usu_sapellido: String, usu_direccion: String, usu_telefono: String, usu_correo: String, usu_genero: String) {
        viewModelScope.launch {
            try {
                val newUser = UsuarioModel(
                    usu_id = usu_id,
                    usu_nombre = usu_nombre,
                    usu_papellido = usu_papellido,
                    usu_sapellido = usu_sapellido,
                    usu_direccion = usu_direccion,
                    usu_telefono = usu_telefono,
                    usu_correo = usu_correo,
                    usu_genero = usu_genero
                )
                val response = RetrofitClient.instance.createUser(newUser)
                _error.value = response.error ?: "Usuario creado"
                fetchUsers()
            } catch (e: Exception) {
                _error.value = "Error al crear usuario: ${e.message}"
            }
        }
    }
}