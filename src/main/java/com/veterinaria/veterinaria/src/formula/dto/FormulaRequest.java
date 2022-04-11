package com.veterinaria.veterinaria.src.formula.dto;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class FormulaRequest {
    Formula formula;
    List<DescripFormula>  descripFormulas;

    private FormulaRequest(Formula formula, List<DescripFormula> descripFormulas) {
        this.formula = formula;
        this.descripFormulas = descripFormulas;
    }
}
