package com.veterinaria.veterinaria.src.mascotas.serializable;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.mascotas.dto.Mascotas;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MascotaSerializable implements JsonDeserializer<Mascotas>, JsonSerializer<Mascotas> {
    @Override
    public Mascotas deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return Mascotas.of(jsonObject.get("cdidentificacion").getAsLong(),jsonObject.get("dsnombre").getAsString(),jsonObject.get("dsraza").getAsString(),
                jsonObject.get("dscolor").getAsString(),jsonObject.get("dsespecie").getAsString(), jsonObject.get("cdcliente").getAsString(),
                LocalDate.parse(jsonObject.get("fechaNa").getAsString(),formatter), jsonObject.get("dssexo").getAsString());
    }

    @Override
    public JsonElement serialize(Mascotas src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdcliente",src.getCdcliente());
        parameters.put("cdidentificacion",src.getCdidentificacion());
        parameters.put("dscolor",src.getDscolor());
        parameters.put("dsespecie",src.getDsespecie());
        parameters.put("dsnombre",src.getDsnombre());
        parameters.put("dsraza",src.getDsraza());
        parameters.put("fechaNa",src.getFechaNa());
        parameters.put("dssexo",src.getDsSexo());
        return context.serialize(parameters);
    }
}
