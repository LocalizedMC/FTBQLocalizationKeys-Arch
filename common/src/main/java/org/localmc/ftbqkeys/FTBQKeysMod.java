package org.localmc.ftbqkeys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import org.apache.commons.io.FileUtils;
import org.localmc.ftbqkeys.command.FTBQKeysCommand;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.TreeMap;

public class FTBQKeysMod {
    public static final String MOD_ID = "ftbqlocalkeys";

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveLang(TreeMap<String, String> transKeys, String lang, File parent) throws IOException {
        File fe = new File(parent, lang.toLowerCase(Locale.ROOT) + ".json");
        FileUtils.write(fe, FTBQKeysMod.gson.toJson(transKeys), StandardCharsets.UTF_8);
    }

    public static void init() {
        System.out.println(FTBQKeysExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());

        CommandRegistrationEvent.EVENT.register(FTBQKeysCommand::serverRegisterCommandsEvent);
    }
}
