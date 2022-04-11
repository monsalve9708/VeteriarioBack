package com.veterinaria.veterinaria.src.mascotas.services;

import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;
import com.veterinaria.veterinaria.src.mascotas.respository.MascostasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MascotasServices {
    MascostasRepository mascostasRepository;
    public Object orquestado(Mascotas mascota){
        if(mascota.getCdidentificacion() == 0){
        return mascostasRepository.createMascota(mascota);
        }else {
        return mascostasRepository.updateMascota(mascota);
        }
    }
    public List<Mascotas> getMascotasByIdentificacion(Long identificacion){
        return mascostasRepository.getMascotasByIdentificacion(identificacion);
    }
    public List<Mascotas> getMascotasByCliente(String cdCliente){
        return mascostasRepository.getMascotasByCliente(cdCliente);
    }
    public boolean deleteMascotas(Long cdMascota){
        return mascostasRepository.deleteMascota(cdMascota);
    }
}
