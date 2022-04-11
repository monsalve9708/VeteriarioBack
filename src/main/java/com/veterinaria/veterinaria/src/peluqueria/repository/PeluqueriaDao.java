package com.veterinaria.veterinaria.src.peluqueria.repository;

import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;
import com.veterinaria.veterinaria.src.peluqueria.dto.Peluqueria;
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
public class PeluqueriaDao {
    JdbcTemplate jdbcTemplate;

    public Peluqueria createPeluqueria(Peluqueria peluqueria){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("peluqueria").usingGeneratedKeyColumns("cdpeluqueria");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("FECHA", peluqueria.getFecha());
        parameters.put("TIPO_PRODUCT", peluqueria.getTipoProducto());
        parameters.put("DESCRIPCION", peluqueria.getDescripcion());
        parameters.put("VALOR", peluqueria.getValor());
        parameters.put("CDUSUARIO", peluqueria.getCdUsuario());
        parameters.put("cdmascota", peluqueria.getCdMascota());
        simpleJdbcInsert.executeAndReturnKey(parameters);
        return peluqueria;
    }
    public List<Peluqueria> getByMascota(Integer cdMascota){
        String sql = "select * from peluqueria where cdmascota = ?";
        Object[] args = {cdMascota};
        RowMapper<Peluqueria> row = (rs, rowNum) -> Peluqueria.of(rs.getLong("CDPELUQUERIA"),rs.getDate("FECHA").toLocalDate(),rs.getString("TIPO_PRODUCT"),
                rs.getString("DESCRIPCION"),rs.getString("VALOR"),rs.getString("CDUSUARIO"),rs.getInt("cdmascota"));
        return Try.of(()-> jdbcTemplate.query(sql,args,row)).getOrNull();
    }
}
