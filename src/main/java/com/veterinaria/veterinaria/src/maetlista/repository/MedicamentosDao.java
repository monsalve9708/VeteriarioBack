package com.veterinaria.veterinaria.src.maetlista.repository;

import com.veterinaria.veterinaria.src.maetlista.dto.Medicamentos;
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
public class MedicamentosDao {
    JdbcTemplate jdbcTemplate;

    public void createMedicamento(String dsMedicamento){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("medicamentos").usingGeneratedKeyColumns("CDMEDICAMENTOS");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("dsmedicamento", dsMedicamento);
        simpleJdbcInsert.execute(parameters);
    }
    public boolean getMedicamento(String dsMedicamento){
        String sql = "Select count(*) as total from medicamentos where dsmedicamento = ?";
        Object[] args = {dsMedicamento};
        RowMapper<Integer> row = (rs, rowNum) -> rs.getInt("total");
        Integer respuesta = Try.of(() -> jdbcTemplate.queryForObject(sql, args,row)).getOrNull();
        if (respuesta < 1) {
            createMedicamento(dsMedicamento);
        }
        return true;
    }
    public List<Medicamentos> getMedicamentos(){
        String sql = "Select * from medicamentos";
        RowMapper<Medicamentos> row = (rs, rowNum) -> Medicamentos.of(rs.getLong("cdmedicamentos"),rs.getString("dsmedicamento"));
        return Try.of(() -> jdbcTemplate.query(sql, row)).getOrNull();
    }
}
