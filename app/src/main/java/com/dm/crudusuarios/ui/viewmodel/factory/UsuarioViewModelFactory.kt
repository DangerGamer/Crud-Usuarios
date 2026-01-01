package com.dm.crudusuarios.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dm.crudusuarios.domain.usecase.DeleteUsersUseCase
import com.dm.crudusuarios.domain.usecase.GetUsersByFilterUseCase
import com.dm.crudusuarios.domain.usecase.GetUsersUseCase
import com.dm.crudusuarios.ui.viewmodel.UsuarioViewModel

class UsuarioViewModelFactory(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersByFilterUseCase: GetUsersByFilterUseCase,
    private val deleteUsersUseCase: DeleteUsersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            return UsuarioViewModel(
                getUsersUseCase,
                getUsersByFilterUseCase,
                deleteUsersUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
