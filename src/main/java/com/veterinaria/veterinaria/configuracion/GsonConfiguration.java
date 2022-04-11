package com.veterinaria.veterinaria.configuracion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.veterinaria.veterinaria.src.formula.dto.DescripFormula;
import com.veterinaria.veterinaria.src.formula.dto.Formula;
import com.veterinaria.veterinaria.src.formula.dto.FormulaRequest;
import com.veterinaria.veterinaria.src.formula.serializable.DescripFormulaAdapter;
import com.veterinaria.veterinaria.src.formula.serializable.FormulaAdapter;
import com.veterinaria.veterinaria.src.formula.serializable.FormulaResquetAdapter;
import com.veterinaria.veterinaria.src.historia.dto.*;
import com.veterinaria.veterinaria.src.historia.serialization.*;
import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;
import com.veterinaria.veterinaria.src.mascotas.serializable.MascotaSerializable;
import com.veterinaria.veterinaria.src.peluqueria.dto.Peluqueria;
import com.veterinaria.veterinaria.src.peluqueria.serializable.PeluqueriaAdapter;
import com.veterinaria.veterinaria.src.usuario.dto.Usuario;
import com.veterinaria.veterinaria.src.usuario.serializable.UsuarioAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {
    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .registerTypeAdapter(Usuario.class,new UsuarioAdapter())
                .registerTypeAdapter(Mascotas.class, new MascotaSerializable())
                .registerTypeAdapter(DesccripHistoria.class,new DescripHistoriaAdapter())
                .registerTypeAdapter(DetalleTabla.class,new DetalleTablaAdapter())
                .registerTypeAdapter(HistoriaClinica.class,new HistoriaClinicaAdapter())
                .registerTypeAdapter(HistoriaClinicaReques.class,new HistoriaClinicaRequesAdapter())
                .registerTypeAdapter(Peluqueria.class,new PeluqueriaAdapter())
                .registerTypeAdapter(Formula.class,new FormulaAdapter())
                .registerTypeAdapter(DescripFormula.class,new DescripFormulaAdapter())
                .registerTypeAdapter(FormulaRequest.class,new FormulaResquetAdapter())
                .registerTypeAdapter(SeguimientoHistoria.class,new SeguimientoHistoriaAdapter())
                .create();
    }
}
