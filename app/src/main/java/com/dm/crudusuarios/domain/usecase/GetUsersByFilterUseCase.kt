package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository

class GetUsersByFilterUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(filter: String): UsuarioResponse<List<UsuarioModel>> {
        return repository.getUserByFilter(filter)
    }
}