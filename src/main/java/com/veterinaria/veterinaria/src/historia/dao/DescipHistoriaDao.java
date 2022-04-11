package com.veterinaria.veterinaria.src.historia.dao;

import com.veterinaria.veterinaria.src.historia.dto.DesccripHistoria;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class DescipHistoriaDao {
        JdbcTemplate jdbcTemplate;

        public Number insertDescrip(DesccripHistoria desccripHistoria,Long cdHistoria){
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("DESCRIP_HISTORIA").usingGeneratedKeyColumns("cddescrip_historia");
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("DSVIVIENDA",desccripHistoria.getDsVivienda());
            parameters.put("NMMASCOTAS",desccripHistoria.getNmMascotas());
            parameters.put("fechaDes",desccripHistoria.getFechaDes());
            parameters.put("fechaVacu",desccripHistoria.getFechaVacu());
            parameters.put("DSENFERMEDADES",desccripHistoria.getDsEnfermedades());
            parameters.put("DSCIRUGIAS",desccripHistoria.getDsCirugias());
            parameters.put("DSALIMENTO",desccripHistoria.getDsAlimento());
            parameters.put("DSFRECUENCIA",desccripHistoria.getDsFrecuencia());
            parameters.put("DSREPRODUCTIVO",desccripHistoria.getDsReproductivo());
            parameters.put("DSTRATAMIENTOS",desccripHistoria.getDsTratamientos());
            parameters.put("DSMOTIVO",desccripHistoria.getDsMotivo());
            parameters.put("DSTEMPERATURA",desccripHistoria.getDsTemperatura());
            parameters.put("DSTEMPERAMENTO",desccripHistoria.getDsTemperamento());
            parameters.put("DSPESO",desccripHistoria.getDsPeso());
            parameters.put("DSFC",desccripHistoria.getDsFc());
            parameters.put("DSFR",desccripHistoria.getDsFr());
            parameters.put("DSTLLC",desccripHistoria.getDsTllc());
            parameters.put("DSMM",desccripHistoria.getDsMm());
            parameters.put("DSPULSO",desccripHistoria.getDsPulso());
            parameters.put("DSACTITUD",desccripHistoria.getDsActitud());
            parameters.put("DSHIDRATACION",desccripHistoria.getDsHidratacion());
            parameters.put("DSNERVIOSO",desccripHistoria.getDsNervioso());
            parameters.put("DSPIEL",desccripHistoria.getDsPiel());
            parameters.put("DSMUSCU",desccripHistoria.getDsMuscu());
            parameters.put("DSRESPIRA",desccripHistoria.getDsRespira());
            parameters.put("DSCARDIO",desccripHistoria.getDsCardio());
            parameters.put("DSDIGES",desccripHistoria.getDsDiges());
            parameters.put("DSURI",desccripHistoria.getDsUri());
            parameters.put("DSLINFOI",desccripHistoria.getDsLinfoi());
            parameters.put("DSREPROD",desccripHistoria.getDsReprod());
            parameters.put("DSOJOS",desccripHistoria.getDsOjos());
            parameters.put("DSOIDOS",desccripHistoria.getDsOidos());
            parameters.put("DSHALLAZ",desccripHistoria.getDsHallaz());
            parameters.put("DSCONSULTA",desccripHistoria.getDsConsulta());
            parameters.put("DSTERAPEU",desccripHistoria.getDsTerapeu());
            parameters.put("DSSEGUI",desccripHistoria.getDsSegui());
            parameters.put("CDHISTORIA",cdHistoria);
            return  simpleJdbcInsert.executeAndReturnKey(parameters);
        }
        public DesccripHistoria update(DesccripHistoria desccripHistoria){
            String sql = "update DESCRIP_HISTORIA set DSVIVIENDA = ?,NMMASCOTAS = ?, fechaDes = ?, fechaVacu = ?, DSENFERMEDADES = ?, DSCIRUGIAS = ?, DSMOTIVO = ?, DSTEMPERATURA = ? , DSTEMPERAMENTO = ?," +
                    "DSPESO = ?, DSFC = ?, DSFR = ?, DSTLLC = ?, DSMM = ?,DSPULSO = ?, DSACTITUD = ?, DSHIDRATACION = ?, DSNERVIOSO = ?, DSPIEL = ?, DSMUSCU = ?, DSRESPIRA = ?, DSCARDIO = ?,DSDIGES = ?," +
                    "DSURI = ?, DSLINFOI = ?, DSREPROD = ?, DSOJOS = ?, DSOIDOS = ?, DSHALLAZ = ?, DSCONSULTA = ?,DSTERAPEU = ?, DSSEGUI = ?, DSALIMENTO = ?, DSFRECUENCIA = ?,DSREPRODUCTIVO = ?,DSTRATAMIENTOS = ?    where cddescrip_historia = ? ";
            Object[] args = {desccripHistoria.getDsVivienda(),desccripHistoria.getNmMascotas(),desccripHistoria.getFechaDes(),desccripHistoria.getFechaVacu(),desccripHistoria.getDsEnfermedades(),desccripHistoria.getDsCirugias(), desccripHistoria.getDsMotivo(),
                    desccripHistoria.getDsTemperatura(),desccripHistoria.getDsTemperamento(),desccripHistoria.getDsPeso(),desccripHistoria.getDsFc(),desccripHistoria.getDsFr(),desccripHistoria.getDsTllc(),
            desccripHistoria.getDsMm(),desccripHistoria.getDsPulso(),desccripHistoria.getDsActitud(),desccripHistoria.getDsHidratacion(),desccripHistoria.getDsNervioso(),desccripHistoria.getDsPiel(),desccripHistoria.getDsMuscu(),
            desccripHistoria.getDsRespira(),desccripHistoria.getDsCardio(),desccripHistoria.getDsDiges(),desccripHistoria.getDsUri(),desccripHistoria.getDsLinfoi(),desccripHistoria.getDsReprod(),desccripHistoria.getDsOjos(),desccripHistoria.getDsOidos(),
            desccripHistoria.getDsHallaz(),desccripHistoria.getDsConsulta(),desccripHistoria.getDsTerapeu(),desccripHistoria.getDsSegui(),desccripHistoria.getDsAlimento(),desccripHistoria.getDsFrecuencia(),desccripHistoria.getDsReproductivo(),desccripHistoria.getDsTratamientos(), desccripHistoria.getCdDescrip()};
            int response = Try.of(()-> jdbcTemplate.update(sql,args)).get();
            if (response == 1){
                return desccripHistoria;
            }
            return null;
        }

        public DesccripHistoria getByCdHistoria(Integer cdHistoria){
            String sql = "SELECT DSVIVIENDA,NMMASCOTAS, fechades, fechavacu, DSENFERMEDADES, DSCIRUGIAS, DSMOTIVO, DSTEMPERATURA , DSTEMPERAMENTO," +
                    "DSPESO, DSFC, DSFR, DSTLLC, DSMM,DSPULSO, DSFRECUENCIA,DSACTITUD,DSREPRODUCTIVO, DSHIDRATACION, DSNERVIOSO, DSPIEL, DSMUSCU , DSRESPIRA, DSRESPIRA, DSCARDIO,DSDIGES," +
                    "DSURI, DSLINFOI, DSREPROD , DSALIMENTO,DSOJOS , DSOIDOS , DSHALLAZ, DSCONSULTA,DSTERAPEU , DSTRATAMIENTOS,DSSEGUI, CDHISTORIA, cddescrip_historia  FROM DESCRIP_HISTORIA where CDHISTORIA = ? ";
            Object[] args = {cdHistoria};
            RowMapper<DesccripHistoria> rowMapper = (rs, rowNum) -> DesccripHistoria.of(rs.getLong("cddescrip_historia"),rs.getString("DSVIVIENDA"),rs.getInt("NMMASCOTAS"),
                    rs.getString("fechades") == null ? null : rs.getDate("fechades").toLocalDate(),rs.getString("fechavacu") == null ? null : rs.getDate("fechavacu").toLocalDate() ,rs.getString("DSENFERMEDADES"),rs.getString("DSCIRUGIAS"),rs.getString("DSMOTIVO"),
                    rs.getString("DSTEMPERATURA"),rs.getString("DSALIMENTO"),rs.getString("DSFRECUENCIA"),rs.getString("DSREPRODUCTIVO"),rs.getString("DSTRATAMIENTOS"),rs.getString("DSTEMPERAMENTO"),rs.getString("DSPESO"),rs.getString("DSFC"),rs.getString("DSFR"),rs.getString("DSTLLC"),rs.getString("DSMM"),
                    rs.getString("DSPULSO"),rs.getString("DSACTITUD"),rs.getString("DSHIDRATACION"),rs.getString("DSNERVIOSO"),rs.getString("DSPIEL"),rs.getString("DSMUSCU"),rs.getString("DSRESPIRA"),rs.getString("DSCARDIO"),
                    rs.getString("DSDIGES"),rs.getString("DSURI"),rs.getString("DSLINFOI"),rs.getString("DSREPROD"),rs.getString("DSOJOS"),rs.getString("DSOIDOS"),rs.getString("DSHALLAZ"),rs.getString("DSCONSULTA"),
                    rs.getString("DSTERAPEU"),rs.getString("DSSEGUI"),rs.getInt("CDHISTORIA"));
            return Try.of(() -> jdbcTemplate.queryForObject(sql,args,rowMapper)).get();
        }
        public List<String> getlistProducto(){
            String sql = "Select DSPRODUCTO From DESCRIP_HISTORIA";
            RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DSPRODUCTO");
            List<String> retur = new ArrayList<>();
            List<String> response = Try.of(() ->jdbcTemplate.query(sql,rowMapper)).get();
            response.forEach(data -> {
                if (!retur.contains(data)) {
                    retur.add(data);
                }
            });
            return retur;
        }
}
