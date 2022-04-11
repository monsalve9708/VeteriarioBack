package com.veterinaria.veterinaria.src.historia.dto;

import lombok.Value;

import java.time.LocalDate;

@Value(staticConstructor = "of")
public class DesccripHistoria {
    Long cdDescrip;
    String dsVivienda;
    Integer nmMascotas;
    LocalDate fechaDes;
    LocalDate fechaVacu;
    String dsEnfermedades;
    String dsCirugias;
    String dsMotivo;
    String dsTemperatura;
    String dsAlimento;
    String dsFrecuencia;
    String dsReproductivo;
    String dsTratamientos;
    String dsTemperamento;
    String dsPeso;
    String dsFc;
    String dsFr;
    String dsTllc;
    String dsMm;
    String dsPulso;
    String dsActitud;
    String dsHidratacion;
    String dsNervioso;
    String dsPiel;
    String dsMuscu;
    String dsRespira;
    String dsCardio;
    String dsDiges;
    String dsUri;
    String dsLinfoi;
    String dsReprod;
    String dsOjos;
    String dsOidos;
    String dsHallaz;
    String dsConsulta;
    String dsTerapeu;
    String dsSegui;
    Integer cdHistoria;

    public DesccripHistoria(Long cdDescrip, String dsVivienda, Integer nmMascotas, LocalDate fechaDes, LocalDate fechaVacu, String dsEnfermedades, String dsCirugias, String dsMotivo, String dsTemperatura, String dsAlimento, String dsFrecuencia, String dsReproductivo, String dsTratamientos, String dsTemperamento, String dsPeso, String dsFc, String dsFr, String dsTllc, String dsMm, String dsPulso, String dsActitud, String dsHidratacion, String dsNervioso, String dsPiel, String dsMuscu, String dsRespira, String dsCardio, String dsDiges, String dsUri, String dsLinfoi, String dsReprod, String dsOjos, String dsOidos, String dsHallaz, String dsConsulta, String dsTerapeu, String dsSegui, Integer cdHistoria) {
        this.cdDescrip = cdDescrip;
        this.dsVivienda = dsVivienda;
        this.nmMascotas = nmMascotas;
        this.fechaDes = fechaDes;
        this.fechaVacu = fechaVacu;
        this.dsEnfermedades = dsEnfermedades;
        this.dsCirugias = dsCirugias;
        this.dsMotivo = dsMotivo;
        this.dsTemperatura = dsTemperatura;
        this.dsAlimento = dsAlimento;
        this.dsFrecuencia = dsFrecuencia;
        this.dsReproductivo = dsReproductivo;
        this.dsTratamientos = dsTratamientos;
        this.dsTemperamento = dsTemperamento;
        this.dsPeso = dsPeso;
        this.dsFc = dsFc;
        this.dsFr = dsFr;
        this.dsTllc = dsTllc;
        this.dsMm = dsMm;
        this.dsPulso = dsPulso;
        this.dsActitud = dsActitud;
        this.dsHidratacion = dsHidratacion;
        this.dsNervioso = dsNervioso;
        this.dsPiel = dsPiel;
        this.dsMuscu = dsMuscu;
        this.dsRespira = dsRespira;
        this.dsCardio = dsCardio;
        this.dsDiges = dsDiges;
        this.dsUri = dsUri;
        this.dsLinfoi = dsLinfoi;
        this.dsReprod = dsReprod;
        this.dsOjos = dsOjos;
        this.dsOidos = dsOidos;
        this.dsHallaz = dsHallaz;
        this.dsConsulta = dsConsulta;
        this.dsTerapeu = dsTerapeu;
        this.dsSegui = dsSegui;
        this.cdHistoria = cdHistoria;
    }
}
