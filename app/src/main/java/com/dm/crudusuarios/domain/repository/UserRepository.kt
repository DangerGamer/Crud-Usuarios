package com.dm.crudusuarios.domain.repository

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import retrofit2.http.Body
import retrofit2.http.Query

interface UserRepository {
    suspend fun getUsers(): UsuarioResponse<List<UsuarioModel>>

    suspend fun getUserByFilter(filter: String): UsuarioResponse<List<UsuarioModel>>

    suspend fun getUserById(filter: Int?): UsuarioResponse<UsuarioModel>

    suspend fun createUser(body: UsuarioModel): UsuarioResponse<Unit>

    suspend fun updateUser(body: UsuarioModel): UsuarioResponse<Unit>

    suspend fun deleteUsers(ids: List<Int>): UsuarioResponse<Unit>
}