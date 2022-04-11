package com.veterinaria.veterinaria.src.planhuellas.services;

import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellasTransa;
import com.veterinaria.veterinaria.src.planhuellas.repository.PlanHuellasTransasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanHuellasTransasServices {
    PlanHuellasTransasRepository planHuellasTransasRepository;
    public PlanHuellasTransasServices(PlanHuellasTransasRepository _planHuellasTransasRepository){
        planHuellasTransasRepository = _planHuellasTransasRepository;
    }
    float total = 0;
    public PlanHuellasTransa createPlanHuellasTransas(PlanHuellasTransa planHuellasTransa){
        return PlanHuellasTransa.of(planHuellasTransasRepository.createPlanHuellasTransas(planHuellasTransa).intValue(),planHuellasTransa.getCdPlanHuellas(),
                planHuellasTransa.getFechaOperacion(),planHuellasTransa.getDescripcion(),planHuellasTransa.getValor(),
                planHuellasTransa.getTipoOperacion());
    }
    public boolean updatePlanHuellasaTransas(PlanHuellasTransa planHuellasTransa){
        return planHuellasTransasRepository.updatePlanHuellasaTransas(planHuellasTransa);
    }
    public boolean deletePlanHuellasaTransas(long cdPlanHuellasTransa){
        return planHuellasTransasRepository.deletePlanHuellasaTransas(cdPlanHuellasTransa);
    }
    public List<PlanHuellasTransa> getPlanbyPlanHuellasTransas(Integer cdPlanHuellas){
        return planHuellasTransasRepository.getPlanbyPlanHuellasTransas(cdPlanHuellas);
    }
    public Float getValorbyPlanHuellasTransas(Integer cdPlanHuellas) {
        total = 0;
        ArrayList<PlanHuellasTransa> listPLanes = (ArrayList<PlanHuellasTransa>) planHuellasTransasRepository.getPlanbyPlanHuellasTransas(cdPlanHuellas);
        if (listPLanes == null) return 0F;
        listPLanes.forEach(data -> {
            if (data.getTipoOperacion() == 0) {
                total += +data.getValor();
            } else {
                total -= data.getValor();
            }
        });

        return total;
    }
}
