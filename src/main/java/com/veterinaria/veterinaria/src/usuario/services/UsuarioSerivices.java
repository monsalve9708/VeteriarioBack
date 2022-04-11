package com.veterinaria.veterinaria.src.usuario.services;

import com.veterinaria.veterinaria.src.usuario.dto.Usuario;
import com.veterinaria.veterinaria.src.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioSerivices {
    UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario){
        if (usuario.getSw()) {
          if (usuarioRepository.update(usuario) == 1){
              return usuario;
          }
          return null;
        }
        return usuarioRepository.createUsuario(usuario);
    }
    public boolean verificarUsuario(String identificacion){
        Usuario usuario = usuarioRepository.getUsuario(identificacion);
        return usuario == null;
    }
    public Usuario getByIdentificacion(String identificacion){
        Usuario usuario = usuarioRepository.getUsuario(identificacion);
        return usuario;
    }
    public boolean verificarUsuarioByCdusuario(String cdUsuario){
        Usuario usuario = usuarioRepository.getPorCdUsuario(cdUsuario);
        return usuario == null;
    }
    public  boolean changePassword(Usuario usuario){
    return usuarioRepository.changePassword(usuario);
    }

    public List<Usuario> getUsuarios(){ return usuarioRepository.getUsuarios(); }
}