package com.veterinaria.veterinaria.src.maetlista.controller;

import com.veterinaria.veterinaria.src.maetlista.dto.Maet_lista;
import com.veterinaria.veterinaria.src.maetlista.dto.Medicamentos;
import com.veterinaria.veterinaria.src.maetlista.services.MaetListaServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/maet")
@AllArgsConstructor
public class MaetListaController {
    MaetListaServices maetListaServices;

    @GetMapping(value = "/getbycdgrupo")
    public ResponseEntity<List<Maet_lista>> getByCdGrupo(@RequestParam(value = "cdGrupo") String cdGrupo){
        List<Maet_lista> maetListas = maetListaServices.getByCdGrupo(cdGrupo);
        if (maetListas == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(maetListas);
    }
    @GetMapping(value = "/getbycd")
    public ResponseEntity<Maet_lista> getByCd(@RequestParam(value = "cdMaet") Long cdMaet){
        Maet_lista maetLista = maetListaServices.getByCd(cdMaet);
        if (maetLista == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(maetLista);
    }
    @GetMapping(value = "/getmedicamentos")
    public ResponseEntity<List<Medicamentos>> getByCd(){
        return ResponseEntity.ok(maetListaServices.getMedicamentos());
    }
}
