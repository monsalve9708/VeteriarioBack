package com.veterinaria.veterinaria.src.formula.controller;


import com.veterinaria.veterinaria.src.formula.dto.FormulaRequest;
import com.veterinaria.veterinaria.src.formula.services.FormulaServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/formula")
@RestController
@AllArgsConstructor
public class FormulaController {
    FormulaServices formulaServices;

    @PostMapping(value = "/orquestador")
    public ResponseEntity<FormulaRequest> orquestador(@RequestBody FormulaRequest formulaRequest){
        FormulaRequest formulaResponse = formulaServices.createFormula(formulaRequest);
        if (formulaResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(formulaResponse);
    }
    @GetMapping(value = "/getbycdhistoria")
    public ResponseEntity<FormulaRequest> getByCdHistoria(@RequestParam(value = "cdHistoria") Integer cdHistoria){
        FormulaRequest formulaResponse = formulaServices.getByCdHistoria(cdHistoria);
        if (formulaResponse != null){
            return ResponseEntity.ok(formulaResponse);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
