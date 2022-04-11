package com.veterinaria.veterinaria.src.usuario.serializable;

import com.google.gson.*;
import com.veterinaria.veterinaria.src.usuario.dto.Usuario;

import java.lang.reflect.Type;

public class UsuarioAdapter implements JsonDeserializer<Usuario> {
    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject root = json.getAsJsonObject();
        return Usuario.of(root.get("nombre").getAsString(),root.get("apellido").getAsString(),root.get("identificacion").getAsString()
        ,root.get("email").getAsString(),root.get("direccion").getAsString(),root.get("tipouser").getAsString(),root.get("telefono").getAsString()
        ,root.get("usuario").getAsString(),root.get("contraseña").getAsString(),root.get("matricula").getAsString(), root.get("sw").getAsBoolean());
    }
}
