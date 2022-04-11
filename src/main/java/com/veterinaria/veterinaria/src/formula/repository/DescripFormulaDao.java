package com.veterinaria.veterinaria.src.formula.repository;

import com.veterinaria.veterinaria.src.formula.dto.DescripFormula;
import com.veterinaria.veterinaria.src.formula.dto.Formula;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class DescripFormulaDao {
    JdbcTemplate jdbcTemplate;

    public Number createDescripFormula(DescripFormula descripFormula, Long cdFormula){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("DESCRIP_FORMULA").usingGeneratedKeyColumns("cddescrip_formula");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("DSMEDICAMENTO", descripFormula.getDsMedicamentos());
        parameters.put("DSDESCRIPCION", descripFormula.getDsDescripcion());
        parameters.put("CDFORMULA", cdFormula);
        return simpleJdbcInsert.executeAndReturnKey(parameters);
    }
    public List<DescripFormula> getByCdHistoria(Integer cdFormula){
        String sql = "SELECT cddescrip_formula,DSMEDICAMENTO,DSDESCRIPCION,CDFORMULA FROM DESCRIP_FORMULA WHERE CDFORMULA = ? ";
        Object[] args = {cdFormula};
        RowMapper<DescripFormula> row = (rs, rowNum) -> DescripFormula.of(rs.getLong("cddescrip_formula"),rs.getString("DSMEDICAMENTO"),rs.getString("DSDESCRIPCION"),rs.getInt("CDFORMULA"));
        return Try.of(()-> jdbcTemplate.query(sql,args,row)).getOrNull();
    }
    public Long updateDescrip(DescripFormula descripFormula){
        String sql = "UPDATE DESCRIP_FORMULA SET  DSMEDICAMENTO = ? ,DSDESCRIPCION = ?  WHERE CDDESCRIP_FORMULA = ? ";
        Object[] args = {descripFormula.getDsMedicamentos(),descripFormula.getDsDescripcion(),descripFormula.getCdDescripFormula()};
        Integer response = Try.of(()-> jdbcTemplate.update(sql,args)).get();
        return response == 1 ? descripFormula.getCdDescripFormula() : 0;
    }
    public void deleteDescrip(Long cdDescripFormula){
        String sql = "DELETE FROM DESCRIP_FORMULA WHERE CDDESCRIP_FORMULA = ? ";
        Object[] args = {cdDescripFormula};
        Try.of(()-> jdbcTemplate.update(sql,args)).getOrNull();
    }
}
