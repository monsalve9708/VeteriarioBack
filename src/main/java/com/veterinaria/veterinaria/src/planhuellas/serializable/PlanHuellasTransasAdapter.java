package com.veterinaria.veterinaria.src.planhuellas.serializable;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.peluqueria.dto.Peluqueria;
import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellas;
import com.veterinaria.veterinaria.src.planhuellas.dto.PlanHuellasTransa;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PlanHuellasTransasAdapter implements JsonSerializer<PlanHuellasTransa>, JsonDeserializer<PlanHuellasTransa> {

    @Override
    public PlanHuellasTransa deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return PlanHuellasTransa.of(jsonObject.get("cdPlanHuellasTransas").getAsLong(),jsonObject.get("cdPlanHuellas").getAsInt(),
                LocalDate.parse(jsonObject.get("fechaOperacion").getAsString()),jsonObject.get("descripcion").getAsString(),jsonObject.get("valor").getAsInt(),
                jsonObject.get("tipoOperacion").getAsInt());
    }

    @Override
    public JsonElement serialize(PlanHuellasTransa src, Type type, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("cdPlanHuellasTransas",src.getCdPlanHuellasTransas());
        parameters.put("cdPlanHuellas",src.getCdPlanHuellas());
        parameters.put("fechaOperacion",src.getFechaOperacion());
        parameters.put("descripcion",src.getDescripcion());
        parameters.put("valor",src.getValor());
        parameters.put("tipoOperacion",src.getTipoOperacion());
        return context.serialize(parameters);
    }
}
