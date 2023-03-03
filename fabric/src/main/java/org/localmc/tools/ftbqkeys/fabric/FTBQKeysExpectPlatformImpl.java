package org.localmc.tools.ftbqkeys.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FTBQKeysExpectPlatformImpl {
    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }
}
