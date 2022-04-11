package com.veterinaria.veterinaria.src.usuario.repository;

import com.veterinaria.veterinaria.src.login.entity.Login;
import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;
import com.veterinaria.veterinaria.src.usuario.dto.Usuario;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UsuarioRepository {
    private BCryptPasswordEncoder passwordEncoder;
    JdbcTemplate jdbcTemplate;

    public Usuario createUsuario(Usuario usuario){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("usuario");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("dsnombre",usuario.getNombre());
        parameters.put("dsapellido",usuario.getApellido());
        parameters.put("cdidentificacion",usuario.getIdentificacion());
        parameters.put("dscontrasena",(passwordEncoder.encode(usuario.getContraseña())));
        parameters.put("dsdireccion",usuario.getDireccion());
        parameters.put("dscorreo",usuario.getEmail());
        parameters.put("dstelefono",usuario.getTelefono());
        parameters.put("dsmatricula",usuario.getMatricula());
        parameters.put("dstipousuario",usuario.getTipoUsu());
        parameters.put("cdusuario",usuario.getUsuario());
        simpleJdbcInsert.execute(parameters);
        return usuario;
    }
    public Usuario getUsuario(String identificacion){
        String sql = "SELECT cdidentificacion,dsnombre,dsapellido,dscontrasena,dsdireccion,dscorreo,dstelefono,dsmatricula,dstipousuario,cdusuario FROM usuario WHERE cdidentificacion = ?";
        Object[] args = {identificacion};
        RowMapper<Usuario> rowMapper = (rs, rowNum) -> Usuario.of(rs.getString("dsnombre"),rs.getString("dsapellido"),rs.getString("cdidentificacion"),
                rs.getString("dscorreo"),rs.getString("dsdireccion"),rs.getString("dstipousuario"),rs.getString("dstelefono"),rs.getString("cdusuario"),
                null,null,false);
        return Try.of(() -> jdbcTemplate.queryForObject(sql,args,rowMapper)).getOrNull();
    }
    public int update(Usuario usuario){
        String sql = "UPDATE usuario SET dsnombre = ?, dsapellido = ?, dsdireccion = ?, dscorreo = ?, dstelefono = ? where cdidentificacion = ?";
        Object[] args = {usuario.getNombre(),usuario.getApellido(),usuario.getDireccion(),usuario.getEmail(),usuario.getTelefono(),usuario.getIdentificacion()};
        return Try.of(() -> jdbcTemplate.update(sql,args)).get();
    }
    public Usuario getPorCdUsuario(String cdUsuario){
        String sql = "SELECT cdidentificacion,dsnombre,dsapellido,dscontrasena,dsdireccion,dscorreo,dstelefono,dsmatricula,dstipousuario,cdusuario FROM usuario WHERE cdusuario = ?";
        Object[] args = {cdUsuario};
        RowMapper<Usuario> rowMapper = (rs, rowNum) -> Usuario.of(rs.getString("dsnombre"),rs.getString("dsapellido"),rs.getString("cdidentificacion"),
                rs.getString("dscorreo"),rs.getString("dsdireccion"),rs.getString("dstipousuario"),rs.getString("dstelefono"),rs.getString("cdusuario"),
                null,null,false);
        return Try.of(() -> jdbcTemplate.queryForObject(sql,args,rowMapper)).getOrNull();
    }
    public Boolean changePassword(Usuario usuario){
        String sql = "UPDATE usuario SET dscontrasena = ? WHERE cdusuario = ?";
        Object[] args = {passwordEncoder.encode(usuario.getContraseña()), usuario.getUsuario()};
        int response = Try.of(() -> jdbcTemplate.update(sql,args)).get();
        return response == 1;
    }
    public List<Usuario> getUsuarios(){
        String sql = "SELECT cdidentificacion,dsnombre,dsapellido,dsdireccion,dscorreo,dstelefono FROM usuario";
        RowMapper<Usuario> rowMapper = (rs, rowNum) -> Usuario.of(rs.getString("dsnombre"),rs.getString("dsapellido"),rs.getString("cdidentificacion"),
                rs.getString("dscorreo"),rs.getString("dsdireccion"),null,rs.getString("dstelefono"),null,
                null,null,false);
        return jdbcTemplate.query(sql,rowMapper);
    }
}
