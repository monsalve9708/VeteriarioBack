package com.veterinaria.veterinaria.src.usuario.controller;

import com.veterinaria.veterinaria.src.usuario.dto.Usuario;
import com.veterinaria.veterinaria.src.usuario.services.UsuarioSerivices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/usuario")
@AllArgsConstructor
public class UsuarioController {
    UsuarioSerivices usuarioServices;

    @PostMapping(value = "/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario  usuarioRes= usuarioServices.createUsuario(usuario);
        if (usuarioRes != null){
            return ResponseEntity.ok(usuarioRes);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @GetMapping(value = "/verif")
    public ResponseEntity<Boolean> verificarUsuario(@RequestParam(value = "identificacion") String identificacion) {
        if (usuarioServices.verificarUsuario(identificacion)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
    @GetMapping(value = "/identificacion")
    public ResponseEntity<Usuario> getbiIdentificacion(@RequestParam(value = "identificacion") String identificacion) {
         Usuario usuario = usuarioServices.getByIdentificacion(identificacion);
         if (usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.badRequest().body(usuario);
    }
    @GetMapping(value = "/verifbycdusuario")
    public ResponseEntity<Boolean> verificarUsuarioByCdUsuario(@RequestParam(value = "cdusuario") String cdUsuario) {
        if (usuarioServices.verificarUsuarioByCdusuario(cdUsuario)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
    @GetMapping(value = "/getusuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioServices.getUsuarios());
    }
    @PostMapping(value = "/change")
    public ResponseEntity<Boolean> changePassword(@RequestBody Usuario usuario) {
        if (usuarioServices.changePassword(usuario)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
}
