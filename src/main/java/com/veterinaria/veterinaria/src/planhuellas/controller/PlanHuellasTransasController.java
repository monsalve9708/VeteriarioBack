package com.veterinaria.veterinaria.src.planhuellas.controller;

import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellas;
import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellasTransa;
import com.veterinaria.veterinaria.src.planhuellas.services.PlanHuellasServices;
import com.veterinaria.veterinaria.src.planhuellas.services.PlanHuellasTransasServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/planhuellastransas")
public class PlanHuellasTransasController {
    PlanHuellasTransasServices planHuellasTransasServices;

    @PostMapping(value = "/create")
    public ResponseEntity<PlanHuellasTransa> create(@RequestBody PlanHuellasTransa planHuellasTransa){
        PlanHuellasTransa planHuellasTransaResponse = planHuellasTransasServices.createPlanHuellasTransas(planHuellasTransa);
        if (planHuellasTransaResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(planHuellasTransaResponse);
    }
    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody PlanHuellasTransa planHuellasTransa){
        boolean planHuellasTransasResponse = planHuellasTransasServices.updatePlanHuellasaTransas(planHuellasTransa);
        if (!planHuellasTransasResponse){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(planHuellasTransasResponse);
    }
    @GetMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestParam("cdplanhuellastransa") long cdPlanHuellasTransa){
        boolean planHuellasTransasResponse = planHuellasTransasServices.deletePlanHuellasaTransas(cdPlanHuellasTransa);
        if (!planHuellasTransasResponse){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(planHuellasTransasResponse);
    }
    @GetMapping(value = "/getcdplanhuellas")
    public ResponseEntity<List<PlanHuellasTransa>> getByPlanHuellas(@RequestParam("cdplanhuellas") Integer cdPlanHuellas){
        List<PlanHuellasTransa> planHuellasTransasList = planHuellasTransasServices.getPlanbyPlanHuellasTransas(cdPlanHuellas);
        return ResponseEntity.ok(planHuellasTransasList);
    }
    @GetMapping(value = "/gettotal")
    public ResponseEntity<Float> getTotal(@RequestParam("cdplanhuellas") Integer cdPlanHuellas){
        Float total = planHuellasTransasServices.getValorbyPlanHuellasTransas(cdPlanHuellas);
        return ResponseEntity.ok(total);
    }
}