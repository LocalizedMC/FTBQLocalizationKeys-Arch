package org.localmc.tools.ftbqkeys.api;

import dev.ftb.mods.ftbquests.quest.ChapterGroup;

import java.util.List;

public interface BaseQuestFileExtension {
    List<ChapterGroup> getChapterGroups();
}
