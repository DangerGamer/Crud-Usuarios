package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository

class DeleteUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(ids: List<Int>): UsuarioResponse<Unit> {
        return repository.deleteUsers(ids)
    }
}