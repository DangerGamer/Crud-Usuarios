package com.dm.crudusuarios.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dm.crudusuarios.domain.model.UsuarioModel
import com.dm.crudusuarios.domain.model.UsuarioResponse
import com.dm.crudusuarios.domain.usecase.DeleteUsersUseCase
import com.dm.crudusuarios.domain.usecase.GetUsersByFilterUseCase
import com.dm.crudusuarios.domain.usecase.GetUsersUseCase
import com.dm.crudusuarios.utils.MainDispatcherRule
import com.dm.crudusuarios.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UsuarioViewModelTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getUsersUseCase: GetUsersUseCase = mockk()
    private val getUsersByFilterUseCase: GetUsersByFilterUseCase = mockk()
    private val deleteUsersUseCase: DeleteUsersUseCase = mockk()

    private lateinit var viewModel: UsuarioViewModel

    @Before
    fun setUp(){
        viewModel = UsuarioViewModel(
            getUsersUseCase,
            getUsersByFilterUseCase,
            deleteUsersUseCase
        )
    }

    @Test
    fun `obtener usuarios exitosamente actualiza users` () = runTest {
        //given
        val usuarios = listOf(
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            ),
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            )
        )

        val response = UsuarioResponse(
            success = true,
            data = usuarios
        )

        coEvery { getUsersUseCase() } returns response

        //when
        viewModel.fetchUsers()

        //then
        assertEquals(usuarios, viewModel.users.getOrAwaitValue())
    }

    @Test
    fun `obtener usuarios respuesta din datos`() = runTest {
        //Given
        val usuarios = emptyList<UsuarioModel>()

        val response = UsuarioResponse(
            success = true,
            data = usuarios,
            message = "No hay datos"
        )

        coEvery { getUsersUseCase() } returns response

        //When
        viewModel.fetchUsers()

        //Then
        assertTrue(response.success)
        assertEquals("No hay datos", response.message)
    }

    @Test
    fun `obtener usuarios respuesta error desconocido` () = runTest {
        //Given
        val usuarios = emptyList<UsuarioModel>()

        val response = UsuarioResponse(
            success = false,
            data = usuarios,
            error = "Error desconocido"
        )

        coEvery { getUsersUseCase() } returns response

        //When
        viewModel.fetchUsers()

        //Then
        assertFalse(response.success)
        assertEquals("Error desconocido", response.error)
    }

    @Test
    fun `obtener usuarios retorna exception` () = runTest {
        //Given
        coEvery { getUsersUseCase() } throws RuntimeException("Network failure")

        //When
        viewModel.fetchUsers()

        //Then
        assertEquals("Error: Network failure", viewModel.error.value)
    }

    @Test
    fun `envio de listado para eliminar usuarios de forma exitosa` () = runTest {
        //Given
        val usuarios = listOf(
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            ),
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            )
        )
        val fakeResponse = UsuarioResponse<Unit>(success = true, data = null, error = null, message = null)
        coEvery { deleteUsersUseCase(any()) } returns fakeResponse

        //When
        viewModel.deleteUsers(usuarios)

        //Then
        assertTrue(viewModel.deleted.getOrAwaitValue())
    }

    @Test
    fun `envio de listado de usuarios para eliminar retorna error` () = runTest {
        //Given
        val usuarios = listOf(
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            ),
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            )
        )
        val fakeResponse = UsuarioResponse<Unit>(
            success = false,
            data = null,
            error = "Error desconocido"
        )
        coEvery { deleteUsersUseCase(any()) } returns fakeResponse

        //When
        viewModel.deleteUsers(usuarios)

        //Then
        assertFalse(fakeResponse.success)
        assertEquals("Error desconocido", viewModel.error.value)
    }

    @Test
    fun `envio de listado de usuarios para eliminar exception` () = runTest {
        //Given
        val usuarios = listOf(
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            ),
            UsuarioModel(
                1,
                "juan",
                "solis",
                "parra",
                "calle 5",
                "3137238908",
                "j@gmal.com",
                "M"
            )
        )
        coEvery { deleteUsersUseCase(any()) } throws RuntimeException("Network failure")

        //When
        viewModel.deleteUsers(usuarios)

        //Then
        assertEquals("Error: Network failure", viewModel.error.value)
    }
}
