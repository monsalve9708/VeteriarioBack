package com.veterinaria.veterinaria.src.historia.services;

import com.veterinaria.veterinaria.src.historia.dao.DescipHistoriaDao;
import com.veterinaria.veterinaria.src.historia.dao.DetalleTablaDao;
import com.veterinaria.veterinaria.src.historia.dao.HistoriaClinicaDao;
import com.veterinaria.veterinaria.src.historia.dao.SeguimientoHistoriaDao;
import com.veterinaria.veterinaria.src.historia.dto.*;
import com.veterinaria.veterinaria.src.maetlista.repository.MedicamentosDao;
import com.veterinaria.veterinaria.src.mascotas.services.MascotasServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoriaClinicaServices {
        HistoriaClinicaDao historiaClinicaDao;
        DescipHistoriaDao descipHistoriaDao;
        DetalleTablaDao detalleTablaDao;
        SeguimientoHistoriaDao seguimientoHistoriaDao;
        MedicamentosDao medicamentosDao;
        MascotasServices mascotasServices;

        public HistoriaClinicaReques orquestador(HistoriaClinicaReques historiaClinicaReques){
            Long key;
            if (historiaClinicaReques.getHistoriaClinica().getCdHistoria() == 0) {
                key = historiaClinicaDao.insertDDetalle(historiaClinicaReques.getHistoriaClinica()).longValue();
            }else{
                key = historiaClinicaReques.getHistoriaClinica().getCdHistoria();
            }
            HistoriaClinica historiaClinica = HistoriaClinica.of(key,historiaClinicaReques.getHistoriaClinica().getCdMascota(),historiaClinicaReques.getHistoriaClinica().getCdUsuario(),historiaClinicaReques.getDesccripHistoria().getFechaDes());
            Long keyDescrip;
                DesccripHistoria descripHistoriaget = historiaClinicaReques.getDesccripHistoria();
            if (historiaClinicaReques.getDesccripHistoria().getCdDescrip() == 0){
                keyDescrip = descipHistoriaDao.insertDescrip(historiaClinicaReques.getDesccripHistoria(), key).longValue();
            }else {
                DesccripHistoria desccripHistoria = descipHistoriaDao.update(historiaClinicaReques.getDesccripHistoria());
                keyDescrip = desccripHistoria.getCdDescrip();
            }
            DesccripHistoria desccripHistoria = DesccripHistoria.of(keyDescrip,descripHistoriaget.getDsVivienda(),descripHistoriaget.getNmMascotas(),descripHistoriaget.getFechaDes(),descripHistoriaget.getFechaVacu(),
                    descripHistoriaget.getDsEnfermedades(),descripHistoriaget.getDsCirugias(),descripHistoriaget.getDsMotivo(),descripHistoriaget.getDsTemperatura(),descripHistoriaget.getDsAlimento(),descripHistoriaget.getDsFrecuencia(),descripHistoriaget.getDsReproductivo(),descripHistoriaget.getDsTratamientos(),descripHistoriaget.getDsTemperamento(),descripHistoriaget.getDsPeso(),
                    descripHistoriaget.getDsFc(),descripHistoriaget.getDsFr(),descripHistoriaget.getDsTllc(),descripHistoriaget.getDsMm(),descripHistoriaget.getDsPulso(),descripHistoriaget.getDsActitud(),descripHistoriaget.getDsHidratacion(),
                    descripHistoriaget.getDsNervioso(),descripHistoriaget.getDsPiel(),descripHistoriaget.getDsMuscu(),descripHistoriaget.getDsRespira(),descripHistoriaget.getDsCardio(),descripHistoriaget.getDsDiges(),descripHistoriaget.getDsUri(),
                    descripHistoriaget.getDsLinfoi(),descripHistoriaget.getDsReprod(),descripHistoriaget.getDsOjos(),descripHistoriaget.getDsOidos(),descripHistoriaget.getDsHallaz(),descripHistoriaget.getDsConsulta(),descripHistoriaget.getDsTerapeu(),
                    descripHistoriaget.getDsSegui(),key.intValue());
            List<DetalleTabla> detalleTablas = new ArrayList<>();
            Long finalKey = key;
            historiaClinicaReques.getDetalleTabla().forEach(data -> {
                if (data.getCdDetalleTabla() == 0) {
                    if (data.getDsComplementa().equals("Selección") && data.getDsDiferencial().equals("Selección") && data.getDsMaestra().equals("Selección") && data.getDsProblema().equals("Selección")){
                        detalleTablas.add(DetalleTabla.of(0L,null,null,null,null,0));
                    }else {
                        Long keyData = detalleTablaDao.insertDDetalle(data, finalKey).longValue();
                        detalleTablas.add(DetalleTabla.of(keyData, data.getDsProblema(), data.getDsMaestra(), data.getDsDiferencial(), data.getDsComplementa(), finalKey.intValue()));
                    }
                }else{
                    if (data.getDsComplementa().equals("Selección") && data.getDsDiferencial().equals("Selección") && data.getDsMaestra().equals("Selección") && data.getDsProblema().equals("Selección")){
                        detalleTablaDao.deleteAll(finalKey);
                    } else if (data.getDsComplementa().equals("Selección")){
                        detalleTablas.add(detalleTablaDao.update(DetalleTabla.of(data.getCdDetalleTabla(),data.getDsProblema(),data.getDsMaestra(),data.getDsDiferencial(),null,data.getCdHistoria())));
                    }else if (data.getDsProblema().equals("Selección")) {
                        detalleTablas.add(detalleTablaDao.update(DetalleTabla.of(data.getCdDetalleTabla(),null,data.getDsMaestra(),data.getDsDiferencial(),data.getDsComplementa(),data.getCdHistoria())));
                    } else if (data.getDsMaestra().equals("Selección")) {
                        detalleTablas.add(detalleTablaDao.update(DetalleTabla.of(data.getCdDetalleTabla(),data.getDsProblema(),null,data.getDsDiferencial(),data.getDsComplementa(),data.getCdHistoria())));
                    } else if (data.getDsDiferencial().equals("Selección")) {
                        detalleTablas.add(detalleTablaDao.update(DetalleTabla.of(data.getCdDetalleTabla(),data.getDsProblema(),data.getDsDiferencial(),null,data.getDsComplementa(),data.getCdHistoria())));
                    }else{
                    detalleTablas.add(detalleTablaDao.update(data));
                    }
                }
            });
            List<SeguimientoHistoria> seguimientoHistorias = new ArrayList<>();
            historiaClinicaReques.getSeguimientoHistorias().forEach(data -> {
                if (data.getCdSeguimiento() == 0) {
                        Long keyData = seguimientoHistoriaDao.insertSeguimiento(data, finalKey).longValue();
                        seguimientoHistorias.add(SeguimientoHistoria.of(keyData,finalKey.intValue(),data.getFecha(),data.getDescripcion(),data.getCdUsuario()));
                }else{
                    seguimientoHistorias.add(seguimientoHistoriaDao.updateSeguimiento(data));
                }
            });
            return HistoriaClinicaReques.of(desccripHistoria,detalleTablas,seguimientoHistorias,historiaClinica);
        }
        public List<HistoriaClinica> getByCdModelo(Integer cdMascota){
            return historiaClinicaDao.getByCdMascota(cdMascota);
        }
        public HistoriaClinicaReques getByCdHistoria(Long cdHistoria){
            HistoriaClinica historiaClinica = historiaClinicaDao.getByCdHistoria(cdHistoria);
            if (historiaClinica != null) {
                DesccripHistoria desccripHistoria = descipHistoriaDao.getByCdHistoria(cdHistoria.intValue());
                List<DetalleTabla> detalleTablas = detalleTablaDao.getByCdHistoria(cdHistoria.intValue());
                List<SeguimientoHistoria> seguimientoHistorias = seguimientoHistoriaDao.getByCdHistoria(cdHistoria.intValue());
                return HistoriaClinicaReques.of(desccripHistoria,detalleTablas,seguimientoHistorias,historiaClinica);
            }
            return null;
        }
    public List<String> getProducto(){
        return descipHistoriaDao.getlistProducto();
        }
    public List<String> getPrblema(){
        return detalleTablaDao.getlistProblemas();
    }
    public List<String> getMaestra(){
        return detalleTablaDao.getlistMaestra();
    }
    public List<String> getdiferencial(){
        return detalleTablaDao.getlistDiferencial();
    }
    public List<String> getComple(){
        return detalleTablaDao.getlistComple();
    }

}
