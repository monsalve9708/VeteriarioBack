package com.veterinaria.veterinaria.src.peluqueria.controller;

import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;
import com.veterinaria.veterinaria.src.peluqueria.dto.Peluqueria;
import com.veterinaria.veterinaria.src.peluqueria.services.PeluqueriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/peluqueria")
public class PeluqueriaController {
    PeluqueriaService peluqueriaService;

    @PostMapping(value = "/orquestador")
    public ResponseEntity<Peluqueria> orquestador(@RequestBody Peluqueria peluqueria){
        Peluqueria peluqueriaResponse = peluqueriaService.orquestador(peluqueria);
        if (peluqueriaResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(peluqueriaResponse);
    }
    @GetMapping(value = "/getbymascota")
    public ResponseEntity<List<Peluqueria>> getMascotasByIdentificacion(@RequestParam(value = "cdMascota")Integer cdMascota){
        List<Peluqueria> peluquerias = peluqueriaService.getByCdMascota(cdMascota);
        return ResponseEntity.ok(peluquerias);
    }
}
