package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CreateUserUseCaseTest{
    private lateinit var repository: UserRepository
    private lateinit var useCase: CreateUserUseCase

    @Before
    fun setUp(){
        repository = mock()
        useCase = CreateUserUseCase(repository)
    }

    @Test
    fun `envio de usuario para creacion exitosa` () = runBlocking {
        val user = UsuarioModel(1, "juan", "solis", "parra", "calle 5", "3137238908", "j@gmal.com", "M")

        val response = UsuarioResponse(
            success = true,
            data = Unit,
            message = "Usuario creado"
        )

        whenever(repository.createUser(user)).thenReturn(response)

        assertTrue(response.success)
    }

    @Test
    fun `envio de usuario para creacion retorna error en la creacion` () = runBlocking {
        val user = UsuarioModel(1, "juan", "solis", "parra", "calle 5", "3137238908", "j@gmal.com", "M")

        val response = UsuarioResponse(
            success = false,
            data = Unit,
            message = "No se pudo crear el usuario",
            error = "Datos incompletos"
        )

        whenever(repository.createUser(user)).thenReturn(response)

        assertFalse(response.success)
        assertEquals("No se pudo crear el usuario", response.message)
        assertEquals("Datos incompletos", response.error)
    }
}