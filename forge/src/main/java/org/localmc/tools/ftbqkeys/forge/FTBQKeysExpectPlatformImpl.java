package org.localmc.tools.ftbqkeys.forge;

import org.localmc.tools.ftbqkeys.FTBQKeysExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class FTBQKeysExpectPlatformImpl {
    /**
     * This is our actual method to {@link FTBQKeysExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
