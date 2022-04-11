package com.veterinaria.veterinaria.src.planhuellas.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class PlanHuellas {
    long cdPlanHuellas;
    Integer diaPago;
    Integer valorPactado;
    String cdUsuarioRegistro;
    String cdCliente;

    public PlanHuellas(long cdPlanHuellas, Integer diaPago, Integer valorPactado, String cdUsuarioRegistro, String cdCliente) {
        this.cdPlanHuellas = cdPlanHuellas;
        this.diaPago = diaPago;
        this.valorPactado = valorPactado;
        this.cdUsuarioRegistro = cdUsuarioRegistro;
        this.cdCliente = cdCliente;
    }
}
