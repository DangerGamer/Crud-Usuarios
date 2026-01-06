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

class GetUsersByFilterUseCaseTest{
    private lateinit var repository: UserRepository
    private lateinit var useCase: GetUsersByFilterUseCase

    @Before
    fun setUp(){
        repository = mockk()
        useCase = GetUsersByFilterUseCase(repository)
    }

    @Test
    fun `filtrar usuarios retorna una coincidencia` () = runBlocking {
        val lista = listOf(UsuarioModel(1, "juan", "solis", "parra", "calle 5", "3137238908", "j@gmal.com", "M"))
        val response = UsuarioResponse(
            success = true,
            data =  lista
        )

        coEvery { repository.getUserByFilter("juan") } returns response

        assertTrue(response.success)
        assertEquals("juan", response.data?.get(0)?.usu_nombre)
    }

    @Test
    fun `filtrar usuarios no retorna coincidencias` () = runBlocking {
        val lista = emptyList<UsuarioModel>()
        val response = UsuarioResponse(
            success = false,
            data =  lista,
            message = "No se hallaron coincidencias"
        )

        coEvery { repository.getUserByFilter("juan") } returns response

        assertFalse(response.success)
    }
}