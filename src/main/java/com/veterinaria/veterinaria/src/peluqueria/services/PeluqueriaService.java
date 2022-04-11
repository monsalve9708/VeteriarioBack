package com.veterinaria.veterinaria.src.peluqueria.services;

import com.veterinaria.veterinaria.src.peluqueria.dto.Peluqueria;
import com.veterinaria.veterinaria.src.peluqueria.repository.PeluqueriaDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PeluqueriaService {
    PeluqueriaDao peluqueriaDao;
     public Peluqueria orquestador(Peluqueria peluqueria){
         if (peluqueria.getCdPeluqueria() == 0){
             return peluqueriaDao.createPeluqueria(peluqueria);
         }
         return null;
     }
     public List<Peluqueria> getByCdMascota(Integer cdMascota){
         return peluqueriaDao.getByMascota(cdMascota);
     }
}
