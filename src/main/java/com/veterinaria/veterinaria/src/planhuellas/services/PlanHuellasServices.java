package com.veterinaria.veterinaria.src.planhuellas.services;

import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellas;
import com.veterinaria.veterinaria.src.planhuellas.dto.Reporte;
import com.veterinaria.veterinaria.src.planhuellas.repository.PlanHuellasRepository;
import com.veterinaria.veterinaria.src.usuario.dto.Usuario;
import com.veterinaria.veterinaria.src.usuario.services.UsuarioSerivices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PlanHuellasServices {
    PlanHuellasRepository planHuellasRepository;
    PlanHuellasTransasServices planHuellasTransasServices;
    UsuarioSerivices usuarioSerivices;

    public PlanHuellas createPlanHuellas(PlanHuellas planHuellas) {
        return PlanHuellas.of(planHuellasRepository.createPlanHuellas(planHuellas).longValue(), planHuellas.getDiaPago(), planHuellas.getValorPactado(), planHuellas.getCdUsuarioRegistro(), planHuellas.getCdCliente());
    }

    public boolean updatePlanHuellasa(PlanHuellas planHuellas) {
        return planHuellasRepository.updatePlanHuellasa(planHuellas);
    }

    public boolean deletePlanHuellasa(long cdPlanHuellas) {
        return planHuellasRepository.deletePlanHuellasa(cdPlanHuellas);
    }

    public List<PlanHuellas> getPlanHuellasByCliente(String cdCliente) {
        return planHuellasRepository.getPlanHuellasByCliente(cdCliente);
    }

    public List<PlanHuellas> getPlanHuellas() {
        return planHuellasRepository.getPlanHuellas();
    }

    public List<Reporte> reportePlanHuellas() {
        List<PlanHuellas> planHuellas = planHuellasRepository.getPlanHuellas();
        List<Reporte> reportes = new ArrayList<>();
        planHuellas.forEach(x -> {
            Usuario usuario =usuarioSerivices.getByIdentificacion(x.getCdCliente());
            Reporte reporte = new Reporte(x.getCdCliente(),usuario.getNombre() + " " + usuario.getApellido(), planHuellasTransasServices.getValorbyPlanHuellasTransas((int) x.getCdPlanHuellas()));
            reportes.add(reporte);
        });
        return reportes;
    }
}
