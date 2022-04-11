package com.veterinaria.veterinaria.src.formula.serializable;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.formula.dto.Formula;

import java.lang.reflect.Type;
import java.util.HashMap;

public class FormulaAdapter implements JsonSerializer<Formula>, JsonDeserializer<Formula> {
    @Override
    public Formula deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return Formula.of(jsonObject.get("cdFormula").getAsLong(),jsonObject.get("cdHistoria").getAsInt(),jsonObject.get("cdUsuario").getAsString());
    }

    @Override
    public JsonElement serialize(Formula src, Type typeOfSrc, JsonSerializationContext context) {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("cdFormula",src.getCdFormula());
        hashMap.put("cdHistoria",src.getCdFormula());
        hashMap.put("cdUsuario",src.getCdFormula());
        return context.serialize(hashMap);
    }
}
