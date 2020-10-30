package dk.vertexspace.constants;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.lang.reflect.Type;

public class Log {


    public static void info(Object... logObjects) {

        String [] args = new String[logObjects.length];
        for(int i = 0; i < logObjects.length; i++){
            args[i] = logObjects[i].toString();
        }


        final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();


       // Gson gson = new Gson();
        String json = gson.toJson(args);
        System.out.println(json);
    }
}

/*
class ClassTypeAdapter implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {
    @Override
    public JsonElement serialize(Class<?> src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

    @Override
    public Class<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return Class.forName(json.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}*/