package com.dm.crudusuarios.model

data class UsuarioModel (
    val usu_id: Int,
    val usu_nombre: String,
    val usu_papellido: String,
    val usu_sapellido: String,
    val usu_direccion: String,
    val usu_telefono: String,
    val usu_correo: String,
    val usu_genero: String
)