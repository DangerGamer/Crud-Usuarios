package com.dm.crudusuarios.domain.usecase

import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetUserByIdUseCaseTest{
    private lateinit var repository: UserRepository
    private lateinit var useCase: GetUserByIdUseCase

    @Before
    fun setUp(){
        repository = mockk()
        useCase = GetUserByIdUseCase(repository)
    }

    @Test
    fun `busqueda de usuario por id retorna respuesta exitosa` () = runBlocking {
        val usuario = UsuarioModel(5, "juan", "solis", "parra", "calle 5", "3137238908", "j@gmal.com", "M")
        val response = UsuarioResponse(
            success = true,
            data = usuario
        )
        coEvery { repository.getUserById(5) } returns response

        assertTrue(response.success)
        assertEquals("juan", response.data?.usu_nombre)
    }

    @Test
    fun `envio de id inexistente retorna error de no encontrado` () = runBlocking {
        val response: UsuarioResponse<UsuarioModel> = UsuarioResponse(
            success = false,
            data = null,
            error = "Usuario no encontrado"
        )
        coEvery { repository.getUserById(5) } returns response

        assertFalse(response.success)
        assertEquals("Usuario no encontrado", response.error)
    }

    @Test
    fun `envio de dato nulo retorna exception`() = runBlocking {
        val exception = assertThrows(IllegalArgumentException::class.java) { runBlocking { useCase.invoke(null) } }
        assertEquals("El id no puede ser null", exception.message)
    }
}