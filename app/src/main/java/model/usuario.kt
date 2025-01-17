class usuario {
    var idUsuario: Int = 0
    var nombre: String = ""
    var email: String = ""
    var contrasena: String = ""
    var rol: String = ""
    var idCompania: Int = 0

    // Método para clonar con modificaciones
    fun clone(
        idUsuario: Int = this.idUsuario,
        nombre: String = this.nombre,
        email: String = this.email,
        contrasena: String = this.contrasena,
        rol: String = this.rol,
        idCompania: Int = this.idCompania

    ): usuario {
        val nuevoUsuario = usuario()
        nuevoUsuario.idUsuario = idUsuario
        nuevoUsuario.nombre = nombre
        nuevoUsuario.email = email
        nuevoUsuario.contrasena = contrasena
        nuevoUsuario.rol = rol
        nuevoUsuario.idCompania = idCompania
        return nuevoUsuario
    }
}
