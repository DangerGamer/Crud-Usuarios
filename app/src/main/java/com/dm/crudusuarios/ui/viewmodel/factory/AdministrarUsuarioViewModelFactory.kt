package com.dm.crudusuarios.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dm.crudusuarios.domain.usecase.CreateUserUseCase
import com.dm.crudusuarios.domain.usecase.GetUserByIdUseCase
import com.dm.crudusuarios.domain.usecase.UpdateUserUseCase
import com.dm.crudusuarios.ui.viewmodel.AdministrarUsuarioViewModel

class AdministrarUsuarioViewModelFactory(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdministrarUsuarioViewModel::class.java)) {
            return AdministrarUsuarioViewModel(
                getUserByIdUseCase,
                createUserUseCase,
                updateUserUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}