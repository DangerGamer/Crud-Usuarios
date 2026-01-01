package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository

class UpdateUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(usuarioModel: UsuarioModel): UsuarioResponse<Unit> {
        requireNotNull(usuarioModel){ "Usuario no puede ser null" }
        return repository.updateUser(usuarioModel)
    }
}