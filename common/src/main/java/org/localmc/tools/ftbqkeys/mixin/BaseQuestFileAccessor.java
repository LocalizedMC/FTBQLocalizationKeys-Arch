package org.localmc.tools.ftbqkeys.mixin;

import dev.ftb.mods.ftbquests.quest.BaseQuestFile;
import dev.ftb.mods.ftbquests.quest.ChapterGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(BaseQuestFile.class)
public interface BaseQuestFileAccessor {
    @Accessor
    List<ChapterGroup> getChapterGroups();
}
