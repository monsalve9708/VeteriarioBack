package com.veterinaria.veterinaria.src.planhuellas.controller;

import com.veterinaria.veterinaria.src.peluqueria.dto.Peluqueria;
import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellas;
import com.veterinaria.veterinaria.src.planhuellas.dto.Reporte;
import com.veterinaria.veterinaria.src.planhuellas.services.PlanHuellasServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/planhuellas")
public class PlanHuellasController {
    PlanHuellasServices planHuellasServices;

    @PostMapping(value = "/create")
    public ResponseEntity<PlanHuellas> create(@RequestBody PlanHuellas planHuellas){
        PlanHuellas planHuellasResponse = planHuellasServices.createPlanHuellas(planHuellas);
        if (planHuellasResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(planHuellasResponse);
    }
    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody PlanHuellas planHuellas){
        boolean planHuellasResponse = planHuellasServices.updatePlanHuellasa(planHuellas);
        if (!planHuellasResponse){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(planHuellasResponse);
    }
    @GetMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestParam("cdplanhuellas") long cdplanhuellas){
        boolean planHuellasResponse = planHuellasServices.deletePlanHuellasa(cdplanhuellas);
        if (!planHuellasResponse){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(planHuellasResponse);
    }
    @GetMapping(value = "/getbycliente")
    public ResponseEntity<List<PlanHuellas>> getByCliente(@RequestParam("cdcliente") String cdCliente){
        List<PlanHuellas> planHuellasList = planHuellasServices.getPlanHuellasByCliente(cdCliente);
        return ResponseEntity.ok(planHuellasList);
    }
    @GetMapping(value = "/get")
    public ResponseEntity<List<PlanHuellas>> get(){
        List<PlanHuellas> planHuellasList = planHuellasServices.getPlanHuellas();
        return ResponseEntity.ok(planHuellasList);
    }
    @GetMapping(value = "/reporte")
    public ResponseEntity<List<Reporte>> reporte(){
        List<Reporte> reportes = planHuellasServices.reportePlanHuellas();
        return ResponseEntity.ok(reportes);
    }
    @GetMapping(value = "/validate")
    public ResponseEntity<Boolean> validate(@RequestParam("cdcliente") String cdCliente){
        List<PlanHuellas> planHuellasList = planHuellasServices.getPlanHuellasByCliente(cdCliente);
        return ResponseEntity.ok(planHuellasList.isEmpty() ? false : true);
    }


}
