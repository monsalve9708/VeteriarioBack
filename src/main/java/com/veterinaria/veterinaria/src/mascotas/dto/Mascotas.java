package com.veterinaria.veterinaria.src.mascotas.dto;

import lombok.Value;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Value(staticConstructor = "of")
public class Mascotas {
    Long cdidentificacion;
    String dsnombre;
    String dsraza;
    String dscolor;
    String dsespecie;
    String cdcliente;
    LocalDate fechaNa;
    String dsSexo;
    private Mascotas(Long cdidentificacion, String dsnombre, String dsraza, String dscolor, String dsespecie, String cdcliente, LocalDate fechaNa, String dsSexo) {
        this.cdidentificacion = cdidentificacion;
        this.dsnombre = dsnombre;
        this.dsraza = dsraza;
        this.dscolor = dscolor;
        this.dsespecie = dsespecie;
        this.cdcliente = cdcliente;
        this.fechaNa = fechaNa;
        this.dsSexo = dsSexo;
    }
}
