package dk.vertexspace.constants;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static dk.vertexspace.constants.NameConstants.MOD_ID;


public class Log {
    private Log(){}

    public static void info(Object... logObjects) {

        String [] args = new String[logObjects.length];
        for(int i = 0; i < logObjects.length; i++){
            args[i] = logObjects[i].toString();
        }
        Logger logger = LogManager.getLogger(MOD_ID);

        final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        String json = gson.toJson(args);
        logger.info(json);
    }
}