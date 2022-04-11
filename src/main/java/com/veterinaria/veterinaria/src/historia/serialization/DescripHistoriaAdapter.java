package com.veterinaria.veterinaria.src.historia.serialization;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.historia.dto.DesccripHistoria;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DescripHistoriaAdapter implements JsonDeserializer<DesccripHistoria> , JsonSerializer<DesccripHistoria>{
    private static DateTimeFormatter patern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public DesccripHistoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject rs = json.getAsJsonObject();
        String fechavacu = Optional.ofNullable(rs.get("fechaVacu")).map(JsonElement::getAsString).orElse(null);
        String fechaDes = Optional.ofNullable(rs.get("fechaDes")).map(JsonElement::getAsString).orElse(null);
        String alimento = Optional.ofNullable(rs.get("dsAlimento")).map(JsonElement::getAsString).orElse(null);
        String frecuencia = Optional.ofNullable(rs.get("dsFrecuencia")).map(JsonElement::getAsString).orElse(null);
        String reproductivo = Optional.ofNullable(rs.get("dsReproductivo")).map(JsonElement::getAsString).orElse(null);
        String tratamientos = Optional.ofNullable(rs.get("dsTratamientos")).map(JsonElement::getAsString).orElse(null);
        return DesccripHistoria.of(rs.get("cdDescripHistoria").getAsLong(),rs.get("dsVivienda").getAsString(),rs.get("nmMascota").getAsInt(),
                fechaDes == null ? null : LocalDate.parse(rs.get("fechaDes").getAsString(),patern), fechavacu == null ? null :LocalDate.parse(rs.get("fechaVacu").getAsString(),patern),rs.get("dsEnfermedades").getAsString(),rs.get("dsCirugias").getAsString(),rs.get("dsMotivo").getAsString(),
                rs.get("dsTemperatura").getAsString(),alimento,frecuencia,reproductivo,tratamientos,rs.get("dsTemperamento").getAsString(),rs.get("dsPeso").getAsString(),rs.get("dsFc").getAsString(),rs.get("dsFr").getAsString(),rs.get("dsTllc").getAsString(),rs.get("dsMm").getAsString(),
                rs.get("dsPulso").getAsString(),rs.get("dsActitud").getAsString(),rs.get("dsHidratacion").getAsString(),rs.get("dsNervioso").getAsString(),rs.get("dsPiel").getAsString(),rs.get("dsMuscu").getAsString(),rs.get("dsRespira").getAsString(),rs.get("dsCardio").getAsString(),
                rs.get("dsDiges").getAsString(),rs.get("dsUri").getAsString(),rs.get("dsLinfoi").getAsString(),rs.get("dsReprod").getAsString(),rs.get("dsOjos").getAsString(),rs.get("dsOidos").getAsString(),rs.get("dsHallaz").getAsString(),rs.get("dsConsulta").getAsString(),
                rs.get("dsTerapeu").getAsString(),rs.get("dsSegu").getAsString(),rs.get("cdHistoria").getAsInt());
    }

    @Override
    public JsonElement serialize(DesccripHistoria src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdDescripHistoria",src.getCdDescrip());
        parameters.put("dsVivienda",src.getDsVivienda());
        parameters.put("nmMascota",src.getNmMascotas());
        parameters.put("fechaDes",src.getFechaDes());
        parameters.put("fechaVacu",src.getFechaVacu());
        parameters.put("dsEnfermedades",src.getDsEnfermedades());
        parameters.put("dsCirugias",src.getDsCirugias());
        parameters.put("dsMotivo",src.getDsMotivo());
        parameters.put("dsAlimento",src.getDsAlimento());
        parameters.put("dsFrecuencia",src.getDsFrecuencia());
        parameters.put("dsReproductivo",src.getDsReproductivo());
        parameters.put("dsTratamientos",src.getDsTratamientos());
        parameters.put("dsTemperatura",src.getDsTemperatura());
        parameters.put("dsTemperamento",src.getDsTemperamento());
        parameters.put("dsPeso",src.getDsPeso());
        parameters.put("dsFc",src.getDsFc());
        parameters.put("dsFr",src.getDsFr());
        parameters.put("dsTllc",src.getDsTllc());
        parameters.put("dsMm",src.getDsMm());
        parameters.put("dsPulso",src.getDsPulso());
        parameters.put("dsActitud",src.getDsActitud());
        parameters.put("dsHidratacion",src.getDsHidratacion());
        parameters.put("dsNervioso",src.getDsNervioso());
        parameters.put("dsPiel",src.getDsPiel());
        parameters.put("dsMuscu",src.getDsMuscu());
        parameters.put("dsRespira",src.getDsRespira());
        parameters.put("dsCardio",src.getDsCardio());
        parameters.put("dsDiges",src.getDsDiges());
        parameters.put("dsUri",src.getDsUri());
        parameters.put("dsLinfoi",src.getDsLinfoi());
        parameters.put("dsReprod",src.getDsReprod());
        parameters.put("dsOjos",src.getDsOjos());
        parameters.put("dsOidos",src.getDsOidos());
        parameters.put("dsHallaz",src.getDsHallaz());
        parameters.put("dsConsulta",src.getDsConsulta());
        parameters.put("dsTerapeu",src.getDsTerapeu());
        parameters.put("dsSegu",src.getDsSegui());
        parameters.put("cdHistoria",src.getCdHistoria());
        return context.serialize(parameters);
    }
}
