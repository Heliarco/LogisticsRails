package dk.vertexspace.constants;
import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static dk.vertexspace.constants.NameConstants.MOD_ID;


public class Log {
    private Log(){}

    public static void info(PlayerEntity p, Object ... logObjects )
    {
        String r = info(logObjects);

        // Log to chat
        if(!p.getEntityWorld().isRemote) {
            p.sendMessage(new StringTextComponent(r), p.getUniqueID());
        }
    }


    public static String info(Object... logObjects) {
        // Log to local log
        String [] args = new String[logObjects.length];
        for(int i = 0; i < logObjects.length; i++){
            args[i] = logObjects[i].toString();
        }
        Logger logger = LogManager.getLogger(MOD_ID);

        final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        String json = gson.toJson(args);
        logger.info(json);
        return json;


    }
}