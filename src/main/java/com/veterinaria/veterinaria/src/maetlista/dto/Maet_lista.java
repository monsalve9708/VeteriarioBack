package com.veterinaria.veterinaria.src.maetlista.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class Maet_lista {
    Long cdMaetLista;
    String cdGrupo;
    String dsValor;

    private Maet_lista(Long cdMaetLista, String cdGrupo, String dsValor) {
        this.cdMaetLista = cdMaetLista;
        this.cdGrupo = cdGrupo;
        this.dsValor = dsValor;
    }
}
