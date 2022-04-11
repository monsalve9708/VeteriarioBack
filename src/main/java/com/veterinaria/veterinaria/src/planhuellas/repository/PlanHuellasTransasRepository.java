package com.veterinaria.veterinaria.src.planhuellas.repository;

import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellas;
import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellasTransa;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
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
public class PlanHuellasTransasRepository {
    JdbcTemplate jdbcTemplate;

    public Number createPlanHuellasTransas(PlanHuellasTransa planHuellasTransa){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("planhuellas_transa").usingGeneratedKeyColumns("cdplanhuellas_transas");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("CDPLANHUELLAS", planHuellasTransa.getCdPlanHuellas());
        parameters.put("FECHAOPERACION", planHuellasTransa.getFechaOperacion());
        parameters.put("DESCRIPCION", planHuellasTransa.getDescripcion());
        parameters.put("VALOR", planHuellasTransa.getValor());
        parameters.put("TIPOPERACION", planHuellasTransa.getTipoOperacion());
        return simpleJdbcInsert.executeAndReturnKey(parameters);
    }
    public boolean updatePlanHuellasaTransas(PlanHuellasTransa planHuellasTransa){
        String sql = "Update planhuellas_transa Set DESCRIPCION = ?,VALOR = ?,TIPOPERACION = ? Where cdplanhuellas_transas = ?";
        Object[] args = {planHuellasTransa.getDescripcion(),planHuellasTransa.getValor(),planHuellasTransa.getTipoOperacion(),planHuellasTransa.getCdPlanHuellasTransas()};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        return response == 1;
    }
    public boolean deletePlanHuellasaTransas(long cdPlanHuellasTransa){
        String sql = "Update planhuellas_transa Set FEBAJA = ? Where cdplanhuellas_transas = ?";
        Object[] args = {LocalDate.now(), cdPlanHuellasTransa};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        return response == 1;
    }
    public List<PlanHuellasTransa> getPlanbyPlanHuellasTransas(Integer cdPlanHuellas){
        String sql = "SELECT cdplanhuellas_transas,CDPLANHUELLAS,FECHAOPERACION,DESCRIPCION,VALOR,TIPOPERACION,FEBAJA FROM planhuellas_transa WHERE CDPLANHUELLAS = ? and FEBAJA is null ORDER BY cdplanhuellas_transas DESC  ";
        Object[] args = {cdPlanHuellas};
        RowMapper<PlanHuellasTransa> rowMapper = (rs, rowNum) -> PlanHuellasTransa.of(rs.getLong("cdplanhuellas_transas"),rs.getInt("CDPLANHUELLAS"),
                rs.getDate("FECHAOPERACION").toLocalDate(),rs.getString("DESCRIPCION"),rs.getInt("VALOR"),rs.getInt("TIPOPERACION"));
        return Try.of(() -> jdbcTemplate.query(sql,args,rowMapper)).getOrNull();
    }

}
