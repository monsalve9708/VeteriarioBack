package com.veterinaria.veterinaria.src.maetlista.services;

import com.veterinaria.veterinaria.src.maetlista.dto.Maet_lista;
import com.veterinaria.veterinaria.src.maetlista.dto.Medicamentos;
import com.veterinaria.veterinaria.src.maetlista.repository.MaetListRepository;
import com.veterinaria.veterinaria.src.maetlista.repository.MedicamentosDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaetListaServices {
        MaetListRepository maet_listRepository;
        MedicamentosDao medicamentosDao;

        public List<Maet_lista> getByCdGrupo(String cdGrupo){
            return maet_listRepository.getByCdGrupo(cdGrupo);
        }
        public Maet_lista getByCd(Long cdMaet){
            return maet_listRepository.getByCd(cdMaet);
        }
        public List<Medicamentos> getMedicamentos() { return medicamentosDao.getMedicamentos();}
}
