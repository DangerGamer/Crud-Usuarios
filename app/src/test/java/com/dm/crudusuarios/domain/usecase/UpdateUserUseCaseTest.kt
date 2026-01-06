package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UpdateUserUseCaseTest{
    private lateinit var repository: UserRepository
    private lateinit var useCase: UpdateUserUseCase

    @Before
    fun setUp(){
        repository = mockk()
        useCase = UpdateUserUseCase(repository)
    }

    @Test
    fun `envio de datos de usuario para actualizacion exitosa` () = runBlocking {
        val user = UsuarioModel(1, "juan", "solis", "parra", "calle 5", "3137238908", "j@gmal.com", "M")

        val response = UsuarioResponse(
            success = true,
            data = Unit,
            message = "Usuario actualizado"
        )

        coEvery { repository.updateUser(user) } returns response

        assertTrue(response.success)
    }

    @Test
    fun `envio de datos de usuario para actualizacion retorna error en la creacion` () = runBlocking {
        val user = UsuarioModel(1, "juan", "solis", "parra", "calle 5", "3137238908", "j@gmal.com", "M")

        val response = UsuarioResponse(
            success = false,
            data = Unit,
            message = "No se pudo actualizar el usuario",
            error = "No existe el id"
        )

        coEvery { repository.updateUser(user) } returns response

        assertFalse(response.success)
        assertEquals("No se pudo actualizar el usuario", response.message)
        assertEquals("No existe el id", response.error)
    }
}