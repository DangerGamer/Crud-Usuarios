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
}
