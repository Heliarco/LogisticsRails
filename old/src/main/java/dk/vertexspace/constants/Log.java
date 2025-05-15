package dk.vertexspace.constants;
import com.google.gson.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static dk.vertexspace.constants.NameConstants.MOD_ID;


public class Log {
    private Log(){}




    public static void chat(PlayerEntity p, Object ... logObjects )
    {
        String r = serialize(logObjects);

        // Log to chat
        if(p.getEntityWorld().isRemote) {
            p.sendMessage(new StringTextComponent(r), p.getUniqueID());
        }
    }

    private static String serialize(Object ... logObjects) {
        String [] args = new String[logObjects.length];
        for(int i = 0; i < logObjects.length; i++) {
            args[i] = logObjects[i].toString();
        }
        if ( logObjects.length == 1){
            return asString(logObjects[0]);
        }
        else {
            return asString(args);
        }
    }

    public static void info(Object... logObjects) {

        Logger logger = LogManager.getLogger(MOD_ID);
        String r = serialize(logObjects);

        logger.info(r);
    }

    private static String asString(Object o){
        final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        try {
            return gson.toJson(o);
        }
        catch (Exception e) {
            return o.toString();
        }
    }
}