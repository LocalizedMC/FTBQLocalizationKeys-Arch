package org.localmc.tools.ftbqkeys.forge;

import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.localmc.tools.ftbqkeys.FTBQKeysMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FTBQKeysMod.MODID)
public class FTBQKeysModForge {
    public FTBQKeysModForge() {
        EventBuses.registerModEventBus(FTBQKeysMod.MODID, FMLJavaModLoadingContext.get().getModEventBus());
        IEventBus modEventBus = EventBuses.getModEventBus(FTBQKeysMod.MODID).get();

        modEventBus.addListener(this::onInitialize);
    }

    public void onInitialize(FMLCommonSetupEvent event) {
        FTBQKeysMod.init();
    }
}
