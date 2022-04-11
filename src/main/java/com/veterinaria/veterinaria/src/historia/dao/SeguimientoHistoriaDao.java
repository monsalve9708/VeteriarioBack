package com.veterinaria.veterinaria.src.historia.dao;

import com.veterinaria.veterinaria.src.historia.dto.DetalleTabla;
import com.veterinaria.veterinaria.src.historia.dto.SeguimientoHistoria;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class SeguimientoHistoriaDao {
    JdbcTemplate jdbcTemplate;

    public Number insertSeguimiento(SeguimientoHistoria seguimientoHistoria, Long cdHistoria){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("SEGUIMIENTO").usingGeneratedKeyColumns("cdseguimiento");
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("FECHA",seguimientoHistoria.getFecha());
        parameter.put("DESCRIPCION",seguimientoHistoria.getDescripcion());
        parameter.put("CDUSUARIO",seguimientoHistoria.getCdUsuario());
        parameter.put("CDHISTORIA",cdHistoria);
        return simpleJdbcInsert.executeAndReturnKey(parameter);
    }
    public SeguimientoHistoria updateSeguimiento(SeguimientoHistoria seguimientoHistoria){
        String sql = "update SEGUIMIENTO set DESCRIPCION = ? where CDSEGUIMIENTO  = ?";
        Object[] args = {seguimientoHistoria.getDescripcion(),seguimientoHistoria.getCdSeguimiento()};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        if (response == 1){
            return seguimientoHistoria;
        }
        return null;
    }
    public List<SeguimientoHistoria> getByCdHistoria(Integer cdHistoria){
        String sql = "SELECT CDSEGUIMIENTO,FECHA,DESCRIPCION,CDUSUARIO,CDHISTORIA FROM SEGUIMIENTO WHERE CDHISTORIA = ?";
        Object[] args = {cdHistoria};
        RowMapper<SeguimientoHistoria> rowMapper = (rs, rowNum) -> SeguimientoHistoria.of(rs.getLong("CDSEGUIMIENTO"),rs.getInt("CDHISTORIA"), Timestamp.valueOf(rs.getString("FECHA")).toLocalDateTime(),
                rs.getString("DESCRIPCION"),rs.getString("CDUSUARIO"));
        return Try.of(()-> jdbcTemplate.query(sql,args,rowMapper)).get();
    }
}
