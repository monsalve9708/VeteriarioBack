package com.veterinaria.veterinaria.src.peluqueria.serializable;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.peluqueria.dto.Peluqueria;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PeluqueriaAdapter implements JsonSerializer<Peluqueria>, JsonDeserializer<Peluqueria> {
    @Override
    public Peluqueria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return Peluqueria.of(0L, LocalDate.now(),jsonObject.get("tipoProducto").getAsString(),jsonObject.get("descripcion").getAsString(),
                jsonObject.get("valor").getAsString(),jsonObject.get("cdUsuario").getAsString(),jsonObject.get("cdMascota").getAsInt());
    }

    @Override
    public JsonElement serialize(Peluqueria src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdPeluqueria",src.getCdPeluqueria());
        parameters.put("fecha",src.getFecha());
        parameters.put("tipoProducto",src.getTipoProducto());
        parameters.put("descripcion",src.getDescripcion());
        parameters.put("valor",src.getValor());
        parameters.put("cdUsuario",src.getCdUsuario());
        parameters.put("cdMascota",src.getCdMascota());
        return context.serialize(parameters);
    }
}
