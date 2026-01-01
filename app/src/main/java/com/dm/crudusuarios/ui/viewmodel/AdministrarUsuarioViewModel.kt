package com.dm.crudusuarios.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.usecase.CreateUserUseCase
import com.dm.crudusuarios.domain.usecase.GetUserByIdUseCase
import com.dm.crudusuarios.domain.usecase.UpdateUserUseCase
import kotlinx.coroutines.launch

class AdministrarUsuarioViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<UsuarioModel?>()
    val user: LiveData<UsuarioModel?> = _user

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    //respuesta de funcion de actualizar
    private val _updated = MutableLiveData<Boolean>()
    val updated: LiveData<Boolean> = _updated

    //respuesta de funcion de crear
    private val _created = MutableLiveData<Boolean>()
    val created: LiveData<Boolean> = _created

    fun fetchUsersById(id: Int?) {
        viewModelScope.launch {
            try {
                val response = getUserByIdUseCase(id)
                if (response.success) {
                    if (response.data != null){
                        _user.value = response.data
                    }else{
                        _user.value = null
                        _error.value = "Usuario no encontrado"
                    }
                } else {
                    _error.value = response.error ?: "Error desconocido"
                }
            } catch (e: Exception) {
                _error.value = "Error fetchUsersById: ${e.message}"
                println("Error fetchUsersById: ${e.message}")
            }
        }
    }

    fun updateUser(user: UsuarioModel){
        viewModelScope.launch {
            try {
                val response = updateUserUseCase(user)
                _updated.value = response.success
                if (!response.success){
                    _error.value = response.error
                }

            } catch (e: Exception){
                _error.value = "Error updateUser: ${e.message}"
                println("Error updateUser: ${e.message}")
            }
        }
    }

    fun createUser(user: UsuarioModel){
        viewModelScope.launch {
            try {
                val response = createUserUseCase(user)
                _updated.value = response.success
                if (!response.success){
                    _error.value = response.error
                }

            } catch (e: Exception){
                _error.value = "Error createUser: ${e.message}"
                println("Error createUser: ${e.message}")
            }
        }
    }


}