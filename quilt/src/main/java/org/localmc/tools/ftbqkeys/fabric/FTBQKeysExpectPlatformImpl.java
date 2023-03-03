package org.localmc.tools.ftbqkeys.fabric;

import org.quiltmc.loader.api.QuiltLoader;

import java.nio.file.Path;

public class FTBQKeysExpectPlatformImpl {
    public static Path getConfigDir() {
        return QuiltLoader.getConfigDir();
    }

    public static Path getGameDir() {
        return QuiltLoader.getGameDir();
    }
}
