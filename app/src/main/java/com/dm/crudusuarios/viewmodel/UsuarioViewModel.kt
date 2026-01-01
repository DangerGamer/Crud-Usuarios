package com.dm.crudusuarios.viewmodel

import RetrofitClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.crudusuarios.model.DeleteUsersRequest
import com.dm.crudusuarios.model.UsuarioModel
import kotlinx.coroutines.launch

class UsuarioViewModel: ViewModel() {

    private val _users = MutableLiveData<List<UsuarioModel>>()
    val users: LiveData<List<UsuarioModel>> = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _deleted = MutableLiveData<Boolean>()
    val deleted: LiveData<Boolean> = _deleted

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

    fun fetchUsersByFilter(filter: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getUserByFilter(filter)
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

    fun deleteUsers(users: List<UsuarioModel>) {
        viewModelScope.launch {
            try {
                val ids = users.map { it.usu_id }

                val response = RetrofitClient.instance.deleteUsers(
                    DeleteUsersRequest(ids)
                )

                if (response.success) {
                    _deleted.value = true
                } else {
                    _error.value = response.error ?: response.message
                }

            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }


}