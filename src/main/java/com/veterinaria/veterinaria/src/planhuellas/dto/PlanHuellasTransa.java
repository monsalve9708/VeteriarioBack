package com.veterinaria.veterinaria.src.planhuellas.dto;

import lombok.Value;

import java.time.LocalDate;

@Value(staticConstructor = "of")
public class PlanHuellasTransa {
    long cdPlanHuellasTransas;
    Integer cdPlanHuellas;
    LocalDate fechaOperacion;
    String descripcion;
    Integer valor;
    Integer tipoOperacion;

    public PlanHuellasTransa(long cdPlanHuellasTransas, Integer cdPlanHuellas, LocalDate fechaOperacion, String descripcion, Integer valor, Integer tipoOperacion) {
        this.cdPlanHuellasTransas = cdPlanHuellasTransas;
        this.cdPlanHuellas = cdPlanHuellas;
        this.fechaOperacion = fechaOperacion;
        this.descripcion = descripcion;
        this.valor = valor;
        this.tipoOperacion = tipoOperacion;
    }
}
