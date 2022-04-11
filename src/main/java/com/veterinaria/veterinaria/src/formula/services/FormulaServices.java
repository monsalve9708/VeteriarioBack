package com.veterinaria.veterinaria.src.formula.services;

import com.veterinaria.veterinaria.src.formula.dto.DescripFormula;
import com.veterinaria.veterinaria.src.formula.dto.Formula;
import com.veterinaria.veterinaria.src.formula.dto.FormulaRequest;
import com.veterinaria.veterinaria.src.formula.repository.DescripFormulaDao;
import com.veterinaria.veterinaria.src.formula.repository.FormulaDao;
import com.veterinaria.veterinaria.src.maetlista.repository.MedicamentosDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FormulaServices {
    FormulaDao formulaDao;
    DescripFormulaDao descripFormulaDao;
    MedicamentosDao medicamentosDao;

    public FormulaRequest createFormula(FormulaRequest formulaRequest){
        Long response;
        List<DescripFormula> descripFormulas = new ArrayList<>();
        if (formulaRequest.getFormula().getCdFormula() == 0) {
            response = formulaDao.createFormula(formulaRequest.getFormula()).longValue();
        } else {
            response = formulaRequest.getFormula().getCdFormula();
        }
        Formula formula = Formula.of(response,formulaRequest.getFormula().getCdHistoria(),formulaRequest.getFormula().getCdUsuario());
        formulaRequest.getDescripFormulas().forEach(data -> {
            long cdDescrip;
            if (data.getCdDescripFormula() == 0) {
                if (!data.getDsMedicamentos().equals("Selección")) {
                    cdDescrip = descripFormulaDao.createDescripFormula(data, response).longValue();
                    descripFormulas.add(DescripFormula.of(cdDescrip, data.getDsMedicamentos(), data.getDsDescripcion(), response.intValue()));
                }else{
                    descripFormulas.add(DescripFormula.of(0L,null,null,response.intValue()));
                }
            } else{
                if (data.getDsMedicamentos().equals("Selección")) {
                    descripFormulaDao.deleteDescrip(data.getCdDescripFormula());
                    descripFormulas.add(DescripFormula.of(0L,null,null,response.intValue()));
                }else {
                    cdDescrip = descripFormulaDao.updateDescrip(data);
                    descripFormulas.add(DescripFormula.of(cdDescrip,data.getDsMedicamentos(),data.getDsDescripcion(),response.intValue()));
                }
            }

            if (!data.getDsMedicamentos().equals("Selección")){
                medicamentosDao.getMedicamento(data.getDsMedicamentos());
            }
        });
        return FormulaRequest.of(formula,descripFormulas);
    }
    public FormulaRequest getByCdHistoria(Integer cdHistoria){
        Formula formula = formulaDao.getByCdHistoria(cdHistoria);
        List<DescripFormula> descripFormulas = new ArrayList<>();
        if (formula != null){
            descripFormulas = descripFormulaDao.getByCdHistoria(formula.getCdFormula().intValue());
        }
        return FormulaRequest.of(formula,descripFormulas);
    }
}
