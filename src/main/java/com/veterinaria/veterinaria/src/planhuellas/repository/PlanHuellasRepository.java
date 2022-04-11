package com.veterinaria.veterinaria.src.planhuellas.repository;

import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellas;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PlanHuellasRepository {
    JdbcTemplate jdbcTemplate;

    public Number createPlanHuellas(PlanHuellas planHuellas){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("planhuellas").usingGeneratedKeyColumns("cdplanhuellas");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("DIAPAGO", planHuellas.getDiaPago());
        parameters.put("VALORPACTADO", planHuellas.getValorPactado());
        parameters.put("CDUSUARIOREGISTRO", planHuellas.getCdUsuarioRegistro());
        parameters.put("CDCLIENTE", planHuellas.getCdCliente());
        return simpleJdbcInsert.executeAndReturnKey(parameters);
    }
    public boolean updatePlanHuellasa(PlanHuellas planHuellas){
        String sql = "Update PLANHUELLAS Set DIAPAGO = ?,VALORPACTADO = ? Where cdplanhuellas = ?";
        Object[] args = {planHuellas.getDiaPago(),planHuellas.getValorPactado(),planHuellas.getCdPlanHuellas()};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        return response == 1;
    }
    public boolean deletePlanHuellasa(long cdPlanHuellas){
        String sql = "Update PLANHUELLAS Set FEBAJA = ? Where cdplanhuellas = ?";
        Object[] args = {LocalDate.now(),cdPlanHuellas};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        return response == 1;
    }
    public List<PlanHuellas> getPlanHuellasByCliente(String cdCliente){
        String sql = "SELECT cdplanhuellas,DIAPAGO,VALORPACTADO,CDUSUARIOREGISTRO,CDCLIENTE FROM PLANHUELLAS WHERE CDCLIENTE = ? and febaja is null";
        Object[] args = {cdCliente};
        RowMapper<PlanHuellas> rowMapper = (rs, rowNum) -> PlanHuellas.of(rs.getLong("cdplanhuellas"),rs.getInt("DIAPAGO"),rs.getInt("VALORPACTADO"),rs.getString("CDUSUARIOREGISTRO"),cdCliente);
        return Try.of(() -> jdbcTemplate.query(sql,args,rowMapper)).getOrNull();
    }
    public List<PlanHuellas> getPlanHuellas(){
        String sql = "SELECT cdplanhuellas,DIAPAGO,VALORPACTADO,CDUSUARIOREGISTRO,CDCLIENTE FROM PLANHUELLAS";
        Object[] args = {};
        RowMapper<PlanHuellas> rowMapper = (rs, rowNum) -> PlanHuellas.of(rs.getLong("cdplanhuellas"),rs.getInt("DIAPAGO"),rs.getInt("VALORPACTADO"),rs.getString("CDUSUARIOREGISTRO"),rs.getString("CDCLIENTE"));
        return Try.of(() -> jdbcTemplate.query(sql,args,rowMapper)).getOrNull();
    }
}
