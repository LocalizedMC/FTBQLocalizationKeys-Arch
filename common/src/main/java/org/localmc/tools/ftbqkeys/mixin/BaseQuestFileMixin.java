package org.localmc.tools.ftbqkeys.mixin;

import dev.ftb.mods.ftbquests.quest.BaseQuestFile;
import dev.ftb.mods.ftbquests.quest.ChapterGroup;
import org.localmc.tools.ftbqkeys.api.BaseQuestFileExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BaseQuestFile.class)
public class BaseQuestFileMixin implements BaseQuestFileExtension {
    @Shadow
    @Final
    List<ChapterGroup> chapterGroups;

    @Override
    public List<ChapterGroup> getChapterGroups() {
        return chapterGroups;
    }
}
