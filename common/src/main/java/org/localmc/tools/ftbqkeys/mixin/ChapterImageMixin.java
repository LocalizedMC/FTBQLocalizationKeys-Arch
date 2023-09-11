package org.localmc.tools.ftbqkeys.mixin;

import dev.ftb.mods.ftbquests.quest.ChapterImage;
import org.localmc.tools.ftbqkeys.api.ChapterImageExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ChapterImage.class)
public class ChapterImageMixin implements ChapterImageExtension {

    @Shadow
    @Final
    private List<String> hover;

    @Override
    public List<String> getHover() {
        return hover;
    }
}
