package com.dm.crudusuarios.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.usecase.DeleteUsersUseCase
import com.dm.crudusuarios.domain.usecase.GetUsersByFilterUseCase
import com.dm.crudusuarios.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch

class UsuarioViewModel (
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersByFilterUseCase: GetUsersByFilterUseCase,
    private val deleteUsersUseCase: DeleteUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<UsuarioModel>>()
    val users: LiveData<List<UsuarioModel>> = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _deleted = MutableLiveData<Boolean>()
    val deleted: LiveData<Boolean> = _deleted

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = getUsersUseCase()
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
                val response = getUsersByFilterUseCase(filter)
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

                val response = deleteUsersUseCase(ids)

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