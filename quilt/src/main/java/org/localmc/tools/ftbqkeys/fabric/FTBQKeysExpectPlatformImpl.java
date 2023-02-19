package org.localmc.tools.ftbqkeys.fabric;

import org.localmc.tools.ftbqkeys.FTBQKeysExpectPlatform;
import org.quiltmc.loader.api.QuiltLoader;

import java.nio.file.Path;

public class FTBQKeysExpectPlatformImpl {
    /**
     * This is our actual method to {@link FTBQKeysExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return QuiltLoader.getConfigDir();
    }
}
