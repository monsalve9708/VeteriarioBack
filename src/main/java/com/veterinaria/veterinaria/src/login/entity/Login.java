package com.veterinaria.veterinaria.src.login.entity;

import lombok.Value;

import java.io.Serializable;

@Value(staticConstructor = "of")
public class Login implements Serializable {
    String username;
    String Password;
    String dstipousuario;

    private Login(String username, String password, String dstipousuario) {
        this.username = username;
        Password = password;
        this.dstipousuario = dstipousuario;
    }
}
