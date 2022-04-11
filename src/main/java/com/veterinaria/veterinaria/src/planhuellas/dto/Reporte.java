package com.veterinaria.veterinaria.src.planhuellas.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class Reporte {
    String cdcliente;
    String nombre;
    Float saldo;

    public Reporte(String cdcliente,String nombre, Float saldo) {
        this.cdcliente = cdcliente;
        this.nombre = nombre;
        this.saldo = saldo;
    }
}
