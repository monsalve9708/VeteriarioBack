package com.veterinaria.veterinaria.src.historia.serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.veterinaria.veterinaria.src.historia.dto.*;

import java.lang.reflect.Type;
import java.util.List;

public class HistoriaClinicaRequesAdapter implements JsonDeserializer<HistoriaClinicaReques>,  JsonSerializer<HistoriaClinicaReques>{
    Type historia = new TypeToken<HistoriaClinica>(){}.getType();
    Type descrip = new TypeToken<DesccripHistoria>(){}.getType();
    Type detalle = new TypeToken<List<DetalleTabla>>(){}.getType();
    Type segui = new TypeToken<List<SeguimientoHistoria>>(){}.getType();
    @Override
    public HistoriaClinicaReques deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement historiaJson = jsonObject.get("historiaClinica").getAsJsonObject();
        HistoriaClinica historiaClinica = context.deserialize(historiaJson,historia);
        JsonElement descripJson = jsonObject.get("desccripHistoria").getAsJsonObject();
        DesccripHistoria desccripHistoria = context.deserialize(descripJson,descrip);
        JsonArray listDetalle = jsonObject.get("detalleTablas").getAsJsonArray();
        List<DetalleTabla> detalleTablas = context.deserialize(listDetalle,detalle);
        JsonArray listSeguimiento = jsonObject.get("seguimientoHistoria").getAsJsonArray();
        List<SeguimientoHistoria> seguimientoHistorias = context.deserialize(listSeguimiento,segui);
        return HistoriaClinicaReques.of(desccripHistoria,detalleTablas,seguimientoHistorias,historiaClinica);
    }

    @Override
    public JsonElement serialize(HistoriaClinicaReques src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        JsonElement historiaElemnt = context.serialize(src.getHistoriaClinica(),historia);
        JsonElement descripElement = context.serialize(src.getDesccripHistoria(),descrip);
        JsonElement detalleElement = context.serialize(src.getDetalleTabla(), detalle);
        JsonElement seguiElement = context.serialize(src.getSeguimientoHistorias(), segui);
        jsonObject.add("historiaClinica",historiaElemnt);
        jsonObject.add("desccripHistoria",descripElement);
        jsonObject.add("detalleTablas",detalleElement);
        jsonObject.add("seguimientoHistoria",seguiElement);
        return jsonObject;
    }
}
