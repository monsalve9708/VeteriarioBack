package com.veterinaria.veterinaria.src.maetlista.repository;

import com.veterinaria.veterinaria.src.maetlista.dto.Maet_lista;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MaetListRepository {
    JdbcTemplate jdbcTemplate;

    public List<Maet_lista> getByCdGrupo(String cdGrupo){
        String sql = "SELECT cdmaet_lista, cdgrupo, dsvalor FROM maet_lista WHERE cdgrupo = ?";
        Object[] args = {cdGrupo};
        RowMapper<Maet_lista> rowMapper = (rs, rowNum) -> Maet_lista.of(rs.getLong("cdmaet_lista"),rs.getString("cdgrupo"),rs.getString("dsvalor"));
        return Try.of(() -> jdbcTemplate.query(sql,args,rowMapper)).getOrNull();
    }
    public Maet_lista getByCd(Long cdMaet){
        String sql = "SELECT cdmaet_lista, cdgrupo, dsvalor FROM maet_lista WHERE cdmaet_lista = ?";
        Object[] args = {cdMaet};
        RowMapper<Maet_lista> rowMapper = (rs, rowNum) -> Maet_lista.of(rs.getLong("cdmaet_lista"),rs.getString("cdgrupo"),rs.getString("dsvalor"));
        return Try.of(() -> jdbcTemplate.queryForObject(sql,args,rowMapper)).getOrNull();
    }
}
