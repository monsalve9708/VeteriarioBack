package com.veterinaria.veterinaria.src.historia.serialization;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.historia.dto.SeguimientoHistoria;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SeguimientoHistoriaAdapter  implements JsonDeserializer<SeguimientoHistoria>, JsonSerializer<SeguimientoHistoria> {
    @Override
    public SeguimientoHistoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject rs = json.getAsJsonObject();
        return SeguimientoHistoria.of(rs.get("cdSeguimiento").getAsLong(),rs.get("cdHistoria").getAsInt(),LocalDateTime.now(),rs.get("descripcion").getAsString(),
                rs.get("cdUsuario").getAsString());
    }

    @Override
    public JsonElement serialize(SeguimientoHistoria src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdSeguimiento",src.getCdSeguimiento());
        parameters.put("cdHistoria",src.getCdHistoria());
        parameters.put("fecha",src.getFecha());
        parameters.put("descripcion",src.getDescripcion());
        parameters.put("cdUsuario",src.getCdUsuario());
        return context.serialize(parameters);
    }
}
