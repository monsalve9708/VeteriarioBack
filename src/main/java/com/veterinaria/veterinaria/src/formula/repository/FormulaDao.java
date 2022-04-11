package com.veterinaria.veterinaria.src.formula.repository;

import com.veterinaria.veterinaria.src.formula.dto.Formula;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Repository
public class FormulaDao {
    JdbcTemplate jdbcTemplate;

    public Number createFormula(Formula formula){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("FORMULA").usingGeneratedKeyColumns("cdformula");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("CDHISTORIA", formula.getCdHistoria());
        parameters.put("CDUSUARIO", formula.getCdUsuario());
        return simpleJdbcInsert.executeAndReturnKey(parameters);
    }
    public Formula getByCdHistoria(Integer cdHistoria){
        String sql = "SELECT CDFORMULA,CDHISTORIA,CDUSUARIO FROM FORMULA WHERE CDHISTORIA = ? ";
        Object[] args = {cdHistoria};
        RowMapper<Formula> row = (rs, rowNum) -> Formula.of(rs.getLong("CDFORMULA"),rs.getInt("CDHISTORIA"),rs.getString("CDUSUARIO"));
        return Try.of(()-> jdbcTemplate.queryForObject(sql,args,row)).getOrNull();
    }
}
