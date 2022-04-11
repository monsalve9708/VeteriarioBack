package com.veterinaria.veterinaria.src.mascotas.controller;

import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;
import com.veterinaria.veterinaria.src.mascotas.services.MascotasServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/mascotas")
public class MascotasController {
    MascotasServices mascotasServices;
    @PostMapping(value = "/orquestador")
    public ResponseEntity<Mascotas> orquestador(@RequestBody Mascotas mascotas){
        Object mascotasResponse = mascotasServices.orquestado(mascotas);
        if (mascotasResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(mascotas);
    }
    @GetMapping(value = "/byidentificacion")
    public ResponseEntity<List<Mascotas>> getMascotasByIdentificacion(@RequestParam(value = "identificacion")Long identificacion){
        List<Mascotas> mascotas = mascotasServices.getMascotasByIdentificacion(identificacion);
        if (mascotas == null || mascotas.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(mascotas);
    }
    @GetMapping(value = "/bycliente")
    public ResponseEntity<List<Mascotas>> getMascotasByCliente(@RequestParam(value = "cdcliente")String cdCliente){
        List<Mascotas> mascotas = mascotasServices.getMascotasByCliente(cdCliente);
        if (mascotas == null || mascotas.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(mascotas);
    }
    @GetMapping(value = "/delete")
    public ResponseEntity<Boolean> deleteMascotas(@RequestParam(value = "cdMascota")Long cdMascota){
        boolean response = mascotasServices.deleteMascotas(cdMascota);
        return ResponseEntity.ok(response);
    }
}
