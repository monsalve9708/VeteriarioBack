package com.veterinaria.veterinaria.src.historia.controller;


import com.veterinaria.veterinaria.src.historia.dto.HistoriaClinica;
import com.veterinaria.veterinaria.src.historia.dto.HistoriaClinicaReques;
import com.veterinaria.veterinaria.src.historia.services.HistoriaClinicaServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping(value = "/api/v1/historia")
@RestController
@AllArgsConstructor
public class HistoriaClinicaController {
    HistoriaClinicaServices historiaClinicaServices;

    @PostMapping(value = "/orquestador")
    public ResponseEntity<HistoriaClinicaReques> orquestador(@RequestBody HistoriaClinicaReques historiaClinicaReques){
        HistoriaClinicaReques historiaClinicaResponse = historiaClinicaServices.orquestador(historiaClinicaReques);
        if (historiaClinicaResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(historiaClinicaResponse);
    }
    @GetMapping(value = "/getbycdmascota")
    public ResponseEntity<List<HistoriaClinica>> getByCdMascota(@RequestParam(value = "cdMascota") Integer cdMascota){
        List<HistoriaClinica> historiaClinica = historiaClinicaServices.getByCdModelo(cdMascota);
        if (historiaClinica != null){
            return ResponseEntity.ok(historiaClinica);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @GetMapping(value = "/getbycdhistoria")
    public ResponseEntity<HistoriaClinicaReques> getByCdHistoria(@RequestParam(value = "cdHistoria") Long cdHistoria){
        HistoriaClinicaReques historiaClinicaReques = historiaClinicaServices.getByCdHistoria(cdHistoria);
        if (historiaClinicaReques != null){
            return ResponseEntity.ok(historiaClinicaReques);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @GetMapping(value = "/getproduc")
    public ResponseEntity<List<String>> getProducto() {
      return ResponseEntity.ok(historiaClinicaServices.getProducto());
    }
    @GetMapping(value = "/getproblema")
    public ResponseEntity<List<String>> getProblema() throws IOException, URISyntaxException {
        return ResponseEntity.ok(historiaClinicaServices.getPrblema());
    }
    @GetMapping(value = "/getmaestra")
    public ResponseEntity<List<String>> getMaestra(){
        return ResponseEntity.ok(historiaClinicaServices.getMaestra());
    }
    @GetMapping(value = "/getdiferencial")
    public ResponseEntity<List<String>> getDiferencial(){
        return ResponseEntity.ok(historiaClinicaServices.getdiferencial());
    }
    @GetMapping(value = "/getcomplementa")
    public ResponseEntity<List<String>> getComplementa(){
        return ResponseEntity.ok(historiaClinicaServices.getComple());
    }

}
