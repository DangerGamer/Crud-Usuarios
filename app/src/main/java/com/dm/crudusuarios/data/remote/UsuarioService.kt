package com.dm.crudusuarios.data.remote

import com.dm.crudusuarios.domain.model.DeleteUsersRequest
import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUsers(): UsuarioResponse<List<UsuarioModel>>

    @GET("users/filter")
    suspend fun getUserByFilter(@Query("filter") filter: String): UsuarioResponse<List<UsuarioModel>>

    @GET("users/byid")
    suspend fun getUserById(@Query("id") id: Int?): UsuarioResponse<UsuarioModel>

    @POST("users/create")
    suspend fun createUser(@Body body: UsuarioModel): UsuarioResponse<Unit>

    @PUT("users/update")
    suspend fun updateUser(@Body body: UsuarioModel): UsuarioResponse<Unit>

    @DELETE("users/delete")
    suspend fun deleteUser(@Query("id") id: Int): UsuarioResponse<Unit>

    @HTTP(method = "DELETE", path = "users/delete", hasBody = true)
    suspend fun deleteUsers(
        @Body request: DeleteUsersRequest
    ): UsuarioResponse<Unit>
}