package com.veterinaria.veterinaria.src.formula.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class DescripFormula {
    Long cdDescripFormula;
    String dsMedicamentos;
    String dsDescripcion;
    Integer cdFormula;

    private DescripFormula(Long cdDescripFormula, String dsMedicamentos, String dsDescripcion, Integer cdFormula) {
        this.cdDescripFormula = cdDescripFormula;
        this.dsMedicamentos = dsMedicamentos;
        this.dsDescripcion = dsDescripcion;
        this.cdFormula = cdFormula;
    }
}
