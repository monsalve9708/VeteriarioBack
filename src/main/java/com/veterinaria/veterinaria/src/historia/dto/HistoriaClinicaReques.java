package com.veterinaria.veterinaria.src.historia.dto;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class HistoriaClinicaReques {
    DesccripHistoria desccripHistoria;
    List<DetalleTabla> detalleTabla;
    List<SeguimientoHistoria> seguimientoHistorias;
    HistoriaClinica historiaClinica;

    public HistoriaClinicaReques(DesccripHistoria desccripHistoria, List<DetalleTabla> detalleTabla, List<SeguimientoHistoria> seguimientoHistorias, HistoriaClinica historiaClinica) {
        this.desccripHistoria = desccripHistoria;
        this.detalleTabla = detalleTabla;
        this.seguimientoHistorias = seguimientoHistorias;
        this.historiaClinica = historiaClinica;
    }

}
