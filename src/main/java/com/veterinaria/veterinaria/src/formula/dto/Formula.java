package com.veterinaria.veterinaria.src.formula.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class Formula {
    Long cdFormula;
    Integer cdHistoria;
    String cdUsuario;

    private Formula(Long cdFormula, Integer cdHistoria, String cdUsuario) {
        this.cdFormula = cdFormula;
        this.cdHistoria = cdHistoria;
        this.cdUsuario = cdUsuario;
    }
}
