package com.dm.crudusuarios.data.repository

import com.dm.crudusuarios.data.remote.ApiService
import com.dm.crudusuarios.domain.model.DeleteUsersRequest
import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository

class UserRepositoryImpl(
    private val apiService: ApiService
): UserRepository {
    override suspend fun getUsers(): UsuarioResponse<List<UsuarioModel>> {
        return apiService.getUsers()
    }

    override suspend fun getUserByFilter(filter: String): UsuarioResponse<List<UsuarioModel>> {
        return apiService.getUserByFilter(filter)
    }

    override suspend fun getUserById(filter: Int?): UsuarioResponse<UsuarioModel> {
        return apiService.getUserById(filter)
    }

    override suspend fun createUser(body: UsuarioModel): UsuarioResponse<Unit> {
        return apiService.createUser(body)
    }

    override suspend fun updateUser(body: UsuarioModel): UsuarioResponse<Unit> {
        return apiService.updateUser(body)
    }

    override suspend fun deleteUsers(ids: List<Int>): UsuarioResponse<Unit> {
        return apiService.deleteUsers(DeleteUsersRequest(ids))
    }
}