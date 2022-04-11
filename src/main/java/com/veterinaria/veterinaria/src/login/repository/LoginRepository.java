package com.veterinaria.veterinaria.src.login.repository;

import com.veterinaria.veterinaria.src.login.entity.Login;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LoginRepository {
    JdbcTemplate jdbcTemplate;

    public Login getLogin(String username){
        String sql = "SELECT cdusuario, dscontrasena, dstipousuario FROM USUARIO WHERE cdusuario = ? and dstipousuario != ?";
        Object[] args = {username, "Cliente"};
        RowMapper<Login> loginRowMapper = (rs,rowNum) -> Login.of(rs.getString("cdusuario"),rs.getString("dscontrasena"), rs.getString("dstipousuario"));
        Login logeado = Try.of(() -> jdbcTemplate.queryForObject(sql,args,loginRowMapper)).get();
        if (logeado == null) {
            return Login.of(" "," ","Administrador");
        }
        return logeado;
    }
}
