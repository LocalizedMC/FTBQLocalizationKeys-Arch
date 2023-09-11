package org.localmc.tools.ftbqkeys.mixin;

import dev.ftb.mods.ftbquests.quest.Chapter;
import dev.ftb.mods.ftbquests.quest.ChapterImage;
import org.localmc.tools.ftbqkeys.api.QuestChapterExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Chapter.class)
public class ChapterMixin implements QuestChapterExtension {

    @Shadow
    @Final
    private List<ChapterImage> images;

    @Override
    public List<ChapterImage> getImages() {
        return images;
    }
}
