package org.localmc.tools.ftbqkeys.fabric;

import org.localmc.tools.ftbqkeys.FTBQKeysMod;
import net.fabricmc.api.ModInitializer;

public class FTBQKeysModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FTBQKeysMod.init();

        //CommandRegistrationCallback.EVENT.register(FTBQKeysCommand::serverRegisterCommandsEvent);
    }
}
