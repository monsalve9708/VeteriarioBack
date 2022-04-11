package com.veterinaria.veterinaria.src.historia.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class SeguimientoHistoria {
    Long cdSeguimiento;
    Integer cdHistoria;
    LocalDateTime fecha;
    String descripcion;
    String cdUsuario;

    public SeguimientoHistoria(Long cdSeguimiento, Integer cdHistoria, LocalDateTime fecha, String descripcion, String cdUsuario) {
        this.cdSeguimiento = cdSeguimiento;
        this.cdHistoria = cdHistoria;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cdUsuario = cdUsuario;
    }
}
