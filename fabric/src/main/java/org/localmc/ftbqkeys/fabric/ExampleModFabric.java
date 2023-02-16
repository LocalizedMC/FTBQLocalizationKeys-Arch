package org.localmc.ftbqkeys.fabric;

import org.localmc.ftbqkeys.FTBQKeysMod;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FTBQKeysMod.init();
    }
}
