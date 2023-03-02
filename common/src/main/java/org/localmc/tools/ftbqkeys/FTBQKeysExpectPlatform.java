package org.localmc.tools.ftbqkeys;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;

import java.nio.file.Path;

public class FTBQKeysExpectPlatform {
    @ExpectPlatform
    public static Path getConfigDir() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getGameDir() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
}
