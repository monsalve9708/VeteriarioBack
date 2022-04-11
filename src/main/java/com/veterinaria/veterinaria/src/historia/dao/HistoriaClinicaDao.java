package com.veterinaria.veterinaria.src.historia.dao;

import com.veterinaria.veterinaria.src.historia.dto.HistoriaClinica;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class HistoriaClinicaDao {
    JdbcTemplate jdbcTemplate;

    public Number insertDDetalle(HistoriaClinica historiaClinica){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("HISTORIA_CLINICA").usingGeneratedKeyColumns("cdhistoria");
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("CDMASCOTA",historiaClinica.getCdMascota());
        parameter.put("CDUSUARIO",historiaClinica.getCdUsuario());
        parameter.put("FECHA_CREACION",historiaClinica.getFechaCreacion());
        return simpleJdbcInsert.executeAndReturnKey(parameter);
    }
    public List<HistoriaClinica> getByCdMascota(Integer cdMascota){
        String sql = "SELECT FECHA_CREACION,CDUSUARIO,cdhistoria,CDMASCOTA FROM  HISTORIA_CLINICA WHERE  CDMASCOTA = ?";
        Object[] args = {cdMascota};
        RowMapper<HistoriaClinica> rowMapper = (rs, rowNum) -> HistoriaClinica.of(rs.getLong("cdhistoria"),rs.getInt("CDMASCOTA"),rs.getString("CDUSUARIO"),rs.getDate("FECHA_CREACION").toLocalDate());
        return Try.of(()-> jdbcTemplate.query(sql,args,rowMapper)).getOrNull();
    }
    public HistoriaClinica getByCdHistoria(Long cdHistoria){
        String sql = "SELECT FECHA_CREACION,CDUSUARIO,cdhistoria,CDMASCOTA FROM  HISTORIA_CLINICA WHERE  cdhistoria = ?";
        Object[] args = {cdHistoria};
        RowMapper<HistoriaClinica> rowMapper = (rs, rowNum) -> HistoriaClinica.of(rs.getLong("cdhistoria"),rs.getInt("CDMASCOTA"),rs.getString("CDUSUARIO"),rs.getDate("FECHA_CREACION").toLocalDate());
        return Try.of(()-> jdbcTemplate.queryForObject(sql,args,rowMapper)).getOrNull();
    }
}
