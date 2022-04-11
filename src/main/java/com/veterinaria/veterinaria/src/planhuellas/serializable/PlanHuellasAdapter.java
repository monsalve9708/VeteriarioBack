package com.veterinaria.veterinaria.src.planhuellas.serializable;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellas;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PlanHuellasAdapter implements JsonSerializer<PlanHuellas>, JsonDeserializer<PlanHuellas> {
    @Override
    public PlanHuellas deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return PlanHuellas.of(jsonObject.get("cdPlanHuellas").getAsLong(),jsonObject.get("diaPago").getAsInt(),jsonObject.get("valorPactado").getAsInt(),
                jsonObject.get("cdUsuarioRegistro").getAsString(),jsonObject.get("cdCliente").getAsString());
    }

    @Override
    public JsonElement serialize(PlanHuellas src, Type type, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdPlanHuellas",src.getCdPlanHuellas());
        parameters.put("diaPago",src.getDiaPago());
        parameters.put("valorPactado",src.getValorPactado());
        parameters.put("cdUsuarioRegistro",src.getCdUsuarioRegistro());
        parameters.put("cdCliente",src.getCdCliente());
        return context.serialize(parameters);
    }
}
