package com.veterinaria.veterinaria.src.maetlista.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class Medicamentos {
    Long cdMedicamento;
    String dsMedicamento;
}
