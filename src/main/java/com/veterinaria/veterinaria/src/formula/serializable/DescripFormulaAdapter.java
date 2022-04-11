package com.veterinaria.veterinaria.src.formula.serializable;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.formula.dto.DescripFormula;

import java.lang.reflect.Type;
import java.util.HashMap;

public class DescripFormulaAdapter implements JsonDeserializer<DescripFormula>, JsonSerializer<DescripFormula> {
    @Override
    public DescripFormula deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return DescripFormula.of(jsonObject.get("cdDescripFormula").getAsLong(),jsonObject.get("dsMedicamentos").getAsString(),jsonObject.get("dsDescripcion").getAsString(),jsonObject.get("cdFormula").getAsInt());
    }

    @Override
    public JsonElement serialize(DescripFormula src, Type typeOfSrc, JsonSerializationContext context) {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("cdDescripFormula",src.getCdDescripFormula());
        hashMap.put("dsMedicamentos",src.getDsMedicamentos());
        hashMap.put("dsDescripcion",src.getDsDescripcion());
        hashMap.put("cdFormula",src.getCdFormula());
        return context.serialize(hashMap);
    }
}
