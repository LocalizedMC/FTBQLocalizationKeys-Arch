package org.localmc.tools.ftbqkeys.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class FTBQKeysExpectPlatformImpl {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }
}
