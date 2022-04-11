package com.veterinaria.veterinaria.src.peluqueria.dto;

import lombok.Value;

import java.time.LocalDate;

@Value(staticConstructor = "of")
public class Peluqueria {
    Long cdPeluqueria;
    LocalDate fecha;
    String tipoProducto;
    String descripcion;
    String valor;
    String cdUsuario;
    Integer cdMascota;

    public Peluqueria(Long cdPeluqueria, LocalDate fecha, String tipoProducto, String descripcion, String valor, String cdUsuario, Integer cdMascota) {
        this.cdPeluqueria = cdPeluqueria;
        this.fecha = fecha;
        this.tipoProducto = tipoProducto;
        this.descripcion = descripcion;
        this.valor = valor;
        this.cdUsuario = cdUsuario;
        this.cdMascota = cdMascota;
    }
}
