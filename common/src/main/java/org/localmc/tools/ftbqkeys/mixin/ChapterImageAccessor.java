package org.localmc.tools.ftbqkeys.mixin;

import dev.ftb.mods.ftbquests.quest.ChapterImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ChapterImage.class)
public interface ChapterImageAccessor {
    @Accessor
    List<String> getHover();
}
