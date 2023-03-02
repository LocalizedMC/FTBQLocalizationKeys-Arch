package org.localmc.tools.ftbqkeys.forge;

import dev.architectury.platform.forge.EventBuses;
import org.localmc.tools.ftbqkeys.FTBQKeysMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FTBQKeysMod.MODID)
public class FTBQKeysModForge {
    public FTBQKeysModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(FTBQKeysMod.MODID, FMLJavaModLoadingContext.get().getModEventBus());
        FTBQKeysMod.init();
    }
}
