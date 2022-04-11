package com.veterinaria.veterinaria.src.historia.serialization;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.historia.dto.HistoriaClinica;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HistoriaClinicaAdapter implements JsonDeserializer<HistoriaClinica>, JsonSerializer<HistoriaClinica> {
    @Override
    public HistoriaClinica deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject rs = json.getAsJsonObject();
        return HistoriaClinica.of(rs.get("cdHistoria").getAsLong(),rs.get("cdMascota").getAsInt(),rs.get("cdUsuario").getAsString(), LocalDate.now());
    }

    @Override
    public JsonElement serialize(HistoriaClinica src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdHistoria",src.getCdHistoria());
        parameters.put("cdMascota",src.getCdMascota());
        parameters.put("cdUsuario",src.getCdUsuario());
        parameters.put("fechaCreacion",src.getFechaCreacion());
        return context.serialize(parameters);
    }
}
