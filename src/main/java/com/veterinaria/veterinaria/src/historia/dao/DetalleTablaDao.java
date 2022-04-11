package com.veterinaria.veterinaria.src.historia.dao;

import com.veterinaria.veterinaria.src.historia.dto.DetalleTabla;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class DetalleTablaDao {
    JdbcTemplate jdbcTemplate;

    public Number insertDDetalle(DetalleTabla detalleTabla, Long cdHistoria){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("DETALLE_TABLAS").usingGeneratedKeyColumns("cddetalle_tabla");
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("DSPROBLEMA",detalleTabla.getDsProblema().equals("Selección") ? null : detalleTabla.getDsProblema());
        parameter.put("DSMAESTRA",detalleTabla.getDsMaestra().equals("Selección") ? null : detalleTabla.getDsMaestra());
        parameter.put("DSDIFERENCIAL",detalleTabla.getDsDiferencial().equals("Selección") ? null : detalleTabla.getDsDiferencial());
        parameter.put("DSCOMPLEMENTA",detalleTabla.getDsComplementa().equals("Selección") ? null : detalleTabla.getDsComplementa());
        parameter.put("CDHISTORIA",cdHistoria);
        return simpleJdbcInsert.executeAndReturnKey(parameter);
    }
    public DetalleTabla update(DetalleTabla detalleTabla){
        String sql = "update DETALLE_TABLAS set DSPROBLEMA = ?,DSMAESTRA = ?,DSDIFERENCIAL = ?,DSCOMPLEMENTA = ? where cddetalle_tabla  = ?";
        Object[] args = {detalleTabla.getDsProblema(),detalleTabla.getDsMaestra(),detalleTabla.getDsDiferencial(),detalleTabla.getDsComplementa(),detalleTabla.getCdDetalleTabla()};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        if (response == 1){
            return detalleTabla;
        }
        return null;
    }
    public List<DetalleTabla> getByCdHistoria(Integer cdHistoria){
        String sql = "SELECT cddetalle_tabla,DSPROBLEMA,DSMAESTRA,DSDIFERENCIAL,DSCOMPLEMENTA,CDHISTORIA FROM DETALLE_TABLAS WHERE CDHISTORIA = ?";
        Object[] args = {cdHistoria};
        RowMapper<DetalleTabla> rowMapper = (rs, rowNum) -> DetalleTabla.of(rs.getLong("cddetalle_tabla"),rs.getString("DSPROBLEMA"),rs.getString("DSMAESTRA"),
                rs.getString("DSDIFERENCIAL"),rs.getString("DSCOMPLEMENTA"),rs.getInt("CDHISTORIA"));
        return Try.of(()-> jdbcTemplate.query(sql,args,rowMapper)).get();
    }
    public List<String> getlistProblemas(){
        String sql = "Select DSPROBLEMA From DETALLE_TABLAS";
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DSPROBLEMA");
        List<String> retur = new ArrayList<>();
        List<String> response = Try.of(() ->jdbcTemplate.query(sql,rowMapper)).get();
        response.forEach(data -> {
            if (!retur.contains(data)) {
                retur.add(data);
            }
        });
        return retur;
    }
    public List<String> getlistMaestra(){
        String sql = "Select DSMAESTRA From DETALLE_TABLAS";
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DSMAESTRA");
        List<String> retur = new ArrayList<>();
        List<String> response = Try.of(() ->jdbcTemplate.query(sql,rowMapper)).get();
        response.forEach(data -> {
            if (!retur.contains(data)) {
                retur.add(data);
            }
        });
        return retur;
    }
    public List<String> getlistDiferencial(){
        String sql = "Select DSDIFERENCIAL From DETALLE_TABLAS";
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DSDIFERENCIAL");
        List<String> retur = new ArrayList<>();
        List<String> response = Try.of(() ->jdbcTemplate.query(sql,rowMapper)).get();
        response.forEach(data -> {
            if (!retur.contains(data)) {
                retur.add(data);
            }
        });
        return retur;
    }
    public List<String> getlistComple(){
        String sql = "Select DSCOMPLEMENTA From DETALLE_TABLAS";
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DSCOMPLEMENTA");
        List<String> retur = new ArrayList<>();
        List<String> response = Try.of(() ->jdbcTemplate.query(sql,rowMapper)).get();
        response.forEach(data -> {
            if (!retur.contains(data)) {
                retur.add(data);
            }
        });
        return retur;
    }
    public void deleteAll(Long cdDetalle){
        String sql = "DELETE FROM DETALLE_TABLAS WHERE cddetalle_tabla";
        Object[] args = {cdDetalle};
        Try.of(() -> jdbcTemplate.update(sql,args));
    }
}
