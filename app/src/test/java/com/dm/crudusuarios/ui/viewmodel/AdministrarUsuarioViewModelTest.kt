package com.dm.crudusuarios.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.usecase.CreateUserUseCase
import com.dm.crudusuarios.domain.usecase.GetUserByIdUseCase
import com.dm.crudusuarios.domain.usecase.UpdateUserUseCase
import com.dm.crudusuarios.utils.MainDispatcherRule
import com.dm.crudusuarios.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class AdministrarUsuarioViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getUserByIdUseCase: GetUserByIdUseCase = mockk()
    private val createUserUseCase: CreateUserUseCase = mockk()
    private val updateUserUseCase: UpdateUserUseCase = mockk()
    private lateinit var viewModel: AdministrarUsuarioViewModel

    @Before
    fun setUp(){
        viewModel = AdministrarUsuarioViewModel(
            getUserByIdUseCase,
            createUserUseCase,
            updateUserUseCase
        )
    }

    @Test
    fun `obtener usuario buscandolo por su id respuesta exitosa` () = runTest {
        //Given
        val usuario = UsuarioModel(
            1,
            "juan",
            "solis",
            "parra",
            "calle 5",
            "3137238908",
            "j@gmal.com",
            "M"
        )

        val response = UsuarioResponse(
            success = true,
            data = usuario
        )

        coEvery { getUserByIdUseCase(any()) } returns response

        //When
        viewModel.fetchUsersById(1)

        //Then
        assertTrue(response.success)
        assertEquals("juan", response.data?.usu_nombre)
    }

    @Test
    fun `obtener usuario buscando por id responde usuario no existe` () = runTest {
        //Given
        val response = UsuarioResponse<UsuarioModel>(
            success = true,
            data = null
        )

        coEvery { getUserByIdUseCase(any()) } returns response

        //When
        viewModel.fetchUsersById(1)

        //Then
        assertTrue(response.success)
        assertEquals("Usuario no encontrado", viewModel.error.value)
    }

    @Test
    fun `envio de informacion de usuario para actualizar exitosamente` () = runTest {
        //Given
        val usuario = UsuarioModel(
            1,
            "juan",
            "solis",
            "parra",
            "calle 5",
            "3137238908",
            "j@gmal.com",
            "M"
        )

        val response = UsuarioResponse<Unit>(
            success = true,
            data = null
        )

        coEvery { updateUserUseCase(any()) } returns response

        //When
        viewModel.updateUser(usuario)

        //Then
        assertTrue(response.success)
        assertTrue(viewModel.updated.value)
    }

    @Test
    fun `envio de informacion de usuario para actualizar retorna error desconocido` () = runTest {
        //Given
        val usuario = UsuarioModel(
            1,
            "juan",
            "solis",
            "parra",
            "calle 5",
            "3137238908",
            "j@gmal.com",
            "M"
        )

        coEvery { updateUserUseCase(any()) } throws RuntimeException("Network failure")

        //When
        viewModel.updateUser(usuario)

        //Then
        assertEquals("Error updateUser: Network failure", viewModel.error.getOrAwaitValue())
    }

    @Test
    fun `envio de informacion para creacion de usuario exitosa` () = runTest {
        val usuario = UsuarioModel(
            1,
            "juan",
            "solis",
            "parra",
            "calle 5",
            "3137238908",
            "j@gmal.com",
            "M"
        )

        val response = UsuarioResponse<Unit>(
            success = true,
            data = null
        )

        coEvery { createUserUseCase(any()) } returns response

        viewModel.createUser(usuario)

        assertTrue(response.success)
        assertTrue(viewModel.created.value)
    }

    @Test
    fun `envio de informacion para creacion de usuario retorna error desconocido` () = runTest {
        val usuario = UsuarioModel(
            1,
            "juan",
            "solis",
            "parra",
            "calle 5",
            "3137238908",
            "j@gmal.com",
            "M"
        )

        coEvery { createUserUseCase(any()) } throws RuntimeException("Network failure")

        viewModel.createUser(usuario)

        assertEquals("Error createUser: Network failure", viewModel.error.getOrAwaitValue())
    }
}