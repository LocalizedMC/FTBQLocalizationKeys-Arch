package org.localmc.tools.ftbqkeys.quilt;

import org.localmc.tools.ftbqkeys.FTBQKeysMod;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class FTBQKeysModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        FTBQKeysMod.init();
    }
}
