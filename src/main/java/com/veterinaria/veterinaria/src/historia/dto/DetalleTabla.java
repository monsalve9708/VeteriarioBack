package com.veterinaria.veterinaria.src.historia.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class DetalleTabla {
    Long cdDetalleTabla;
    String dsProblema;
    String dsMaestra;
    String dsDiferencial;
    String dsComplementa;
    Integer cdHistoria;

    public DetalleTabla(Long cdDetalleTabla, String dsProblema, String dsMaestra, String dsDiferencial, String dsComplementa, Integer cdHistoria) {
        this.cdDetalleTabla = cdDetalleTabla;
        this.dsProblema = dsProblema;
        this.dsMaestra = dsMaestra;
        this.dsDiferencial = dsDiferencial;
        this.dsComplementa = dsComplementa;
        this.cdHistoria = cdHistoria;
    }
}
