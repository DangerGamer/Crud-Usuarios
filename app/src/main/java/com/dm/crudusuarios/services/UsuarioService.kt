package com.dm.crudusuarios.services

import com.dm.crudusuarios.model.UsuarioModel
import com.dm.crudusuarios.model.UsuarioResponse
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUsers(): UsuarioResponse<List<UsuarioModel>>

    @GET("users/show")
    suspend fun getUserById(@Query("id") id: Int): UsuarioResponse<UsuarioModel>

    @POST("users")
    suspend fun createUser(@Body body: UsuarioModel): UsuarioResponse<Unit>

    @DELETE("users/delete")
    suspend fun deleteUser(@Query("id") id: Int): UsuarioResponse<Unit>
}