package edu.neo.tpfinal.interfaces

interface IUser {
    fun validarUsuario(usuario: String, password: String): Boolean

    fun validarUsuarioRepe(usuario: String): String

    fun validarLongPass(cantMinPass: Int): Boolean


}