package com.veterinaria.veterinaria.src.historia.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value(staticConstructor = "of")
public class HistoriaClinica {
    Long cdHistoria;
    Integer cdMascota;
    String cdUsuario;
    LocalDate fechaCreacion;

    private HistoriaClinica(Long cdHistoria, Integer cdMascota, String cdUsuario, LocalDate fechaCreacion) {
        this.cdHistoria = cdHistoria;
        this.cdMascota = cdMascota;
        this.cdUsuario = cdUsuario;
        this.fechaCreacion = fechaCreacion;
    }
}
