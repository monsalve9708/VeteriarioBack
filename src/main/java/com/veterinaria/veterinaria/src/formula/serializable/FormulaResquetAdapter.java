package com.veterinaria.veterinaria.src.formula.serializable;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.veterinaria.veterinaria.src.formula.dto.DescripFormula;
import com.veterinaria.veterinaria.src.formula.dto.Formula;
import com.veterinaria.veterinaria.src.formula.dto.FormulaRequest;

import java.lang.reflect.Type;
import java.util.List;

public class FormulaResquetAdapter implements JsonSerializer<FormulaRequest>, JsonDeserializer<FormulaRequest>{
    Type formula = new TypeToken<Formula>(){}.getType();
    Type descrip = new TypeToken<List<DescripFormula>>(){}.getType();
    @Override
    public FormulaRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement formulaElement = jsonObject.get("formula").getAsJsonObject();
        Formula formulaDes = context.deserialize(formulaElement,formula);
        JsonArray descripArray = jsonObject.get("descripFormula").getAsJsonArray();
        List<DescripFormula> descripFormulas = context.deserialize(descripArray,descrip);
        return FormulaRequest.of(formulaDes,descripFormulas);
    }

    @Override
    public JsonElement serialize(FormulaRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        JsonElement formulaSeria = context.serialize(src.getFormula(),formula);
        JsonElement descripaSeria = context.serialize(src.getDescripFormulas(),descrip);
        jsonObject.add("formula",formulaSeria);
        jsonObject.add("descripFormula",descripaSeria);
        return jsonObject;
    }
}
