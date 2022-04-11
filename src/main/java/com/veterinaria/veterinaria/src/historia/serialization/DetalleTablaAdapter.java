package com.veterinaria.veterinaria.src.historia.serialization;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.historia.dto.DetalleTabla;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DetalleTablaAdapter implements JsonDeserializer<DetalleTabla>, JsonSerializer<DetalleTabla> {
    @Override
    public DetalleTabla deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject rs = json.getAsJsonObject();
        return DetalleTabla.of(rs.get("cdDetalleTabla").getAsLong(),rs.get("dsProblema").getAsString(),rs.get("dsMaestra").getAsString(),
                rs.get("dsDiferencial").getAsString(),rs.get("dsComplementa").getAsString(),rs.get("cdHistoria").getAsInt());
    }

    @Override
    public JsonElement serialize(DetalleTabla src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdDetalleTabla",src.getCdDetalleTabla());
        parameters.put("dsProblema",src.getDsProblema());
        parameters.put("dsMaestra",src.getDsMaestra());
        parameters.put("dsDiferencial",src.getDsDiferencial());
        parameters.put("dsComplementa",src.getDsComplementa());
        parameters.put("cdHistoria",src.getCdHistoria());
        return context.serialize(parameters);
    }
}
