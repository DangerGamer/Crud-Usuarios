package com.dm.crudusuarios.model

import android.os.Parcel
import android.os.Parcelable

data class UsuarioModel (
    val usu_id: Int,
    val usu_nombre: String,
    val usu_papellido: String,
    val usu_sapellido: String,
    val usu_direccion: String,
    val usu_telefono: String,
    val usu_correo: String,
    val usu_genero: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(usu_id)
        parcel.writeString(usu_nombre)
        parcel.writeString(usu_papellido)
        parcel.writeString(usu_sapellido)
        parcel.writeString(usu_direccion)
        parcel.writeString(usu_telefono)
        parcel.writeString(usu_correo)
        parcel.writeString(usu_genero)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UsuarioModel> {
        override fun createFromParcel(parcel: Parcel): UsuarioModel {
            return UsuarioModel(parcel)
        }

        override fun newArray(size: Int): Array<UsuarioModel?> {
            return arrayOfNulls(size)
        }
    }
}