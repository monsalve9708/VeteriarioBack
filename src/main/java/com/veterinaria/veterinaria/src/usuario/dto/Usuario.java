package com.veterinaria.veterinaria.src.usuario.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class Usuario {
    String nombre;
    String apellido;
    String identificacion;
    String email;
    String direccion;
    String tipoUsu;
    String telefono;
    String usuario;
    String contraseña;
    String matricula;
    Boolean sw;

    private Usuario(String nombre, String apellido, String identificacion, String email, String direccion, String tipoUsu, String telefono, String usuario, String contraseña, String matricula, Boolean sw) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.email = email;
        this.direccion = direccion;
        this.tipoUsu = tipoUsu;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.matricula = matricula;
        this.sw = sw;
    }
}
