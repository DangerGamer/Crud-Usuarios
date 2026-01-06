package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository

class GetUserByIdUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(filter: Int?): UsuarioResponse<UsuarioModel> {
        requireNotNull(filter){ "El id no puede ser null" }
        return repository.getUserById(filter)
    }
}