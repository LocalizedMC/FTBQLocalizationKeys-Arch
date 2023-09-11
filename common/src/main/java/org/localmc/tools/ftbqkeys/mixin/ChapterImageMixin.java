package org.localmc.tools.ftbqkeys.mixin;

import dev.ftb.mods.ftbquests.quest.ChapterImage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ChapterImage.class)
public class ChapterImageMixin {

    public ChapterImageMixin() {

    }

    @Shadow @Final private List<String> hover;

    public List<String> getHovers() {
        return this.hover;
    }
}
