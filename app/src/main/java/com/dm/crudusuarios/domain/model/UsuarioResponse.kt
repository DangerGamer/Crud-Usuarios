package com.dm.crudusuarios.domain.model

data class UsuarioResponse <T>(
    val success: Boolean,
    val data: T?,
    val message: String? = null,
    val error: String? = null
)