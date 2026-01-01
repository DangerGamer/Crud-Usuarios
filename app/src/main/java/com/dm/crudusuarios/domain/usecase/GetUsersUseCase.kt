package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository

class GetUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): UsuarioResponse<List<UsuarioModel>> {
        return repository.getUsers()
    }
}
