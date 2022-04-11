package com.veterinaria.veterinaria.src.mascotas.respository;

import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;
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
public class MascostasRepository {
    JdbcTemplate jdbcTemplate;

    public Mascotas createMascota(Mascotas mascotas){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("mascotas").usingGeneratedKeyColumns("cdidentificacion");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("dsnombre", mascotas.getDsnombre());
        parameters.put("dsraza", mascotas.getDsraza());
        parameters.put("dscolor", mascotas.getDscolor());
        parameters.put("dsespecie", mascotas.getDsespecie());
        parameters.put("cdcliente", mascotas.getCdcliente());
        parameters.put("fechana", mascotas.getFechaNa());
        parameters.put("dssexo", mascotas.getDsSexo());
        simpleJdbcInsert.executeAndReturnKey(parameters);
        return mascotas;
    }
    public boolean updateMascota(Mascotas mascota){
        String sql = "Update mascotas Set dsnombre = ?,dsraza = ?, dscolor = ?, dsespecie = ?, fechana = ?, dssexo = ? Where cdidentificacion = ?";
        Object[] args = {mascota.getDsnombre(),mascota.getDsraza(),mascota.getDscolor(),mascota.getDsespecie(),mascota.getFechaNa(),mascota.getDsSexo(),mascota.getCdidentificacion()};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        return response == 1;
    }
    public boolean deleteMascota(Long cdMascota){
        String sql = "Update mascotas Set FECHA_BAJA = ? Where cdidentificacion = ?";
        Object[] args = {LocalDate.now(), cdMascota};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        return response == 1;
    }
    public List<Mascotas> getMascotasByIdentificacion(Long cdidentificacion){
        String sql = "SELECT cdidentificacion,dsnombre,dsraza,dscolor,dsespecie,cdcliente,fechana,dssexo FROM mascotas WHERE cdidentificacion = ? and FECHA_BAJA is null";
        Object[] args = {cdidentificacion};
        RowMapper<Mascotas> rowMapper = (rs,rowNum) -> Mascotas.of(cdidentificacion,rs.getString("dsnombre"),rs.getString("dsraza"),rs.getString("dscolor"),rs.getString("dsespecie"),rs.getString("cdcliente"), LocalDate.parse(rs.getString("fechana")),rs.getString("dssexo"));
        return Try.of(() -> jdbcTemplate.query(sql,args,rowMapper)).getOrNull();
    }
    public List<Mascotas> getMascotasByCliente(String cdCliente){
        String sql = "SELECT cdidentificacion,dsnombre,dsraza,dscolor,dsespecie,cdcliente,fechana,dssexo FROM mascotas WHERE cdcliente = ? and FECHA_BAJA is null ORDER BY cdidentificacion";
        Object[] args = {cdCliente};
        RowMapper<Mascotas> rowMapper = (rs,rowNum) -> Mascotas.of(rs.getLong("cdidentificacion"),rs.getString("dsnombre"),rs.getString("dsraza"),rs.getString("dscolor"),rs.getString("dsespecie"),rs.getString("cdcliente"), LocalDate.parse(rs.getString("fechana")), rs.getString("dssexo"));
        return Try.of(() -> jdbcTemplate.query(sql,args,rowMapper)).getOrNull();
    }
}
