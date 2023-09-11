package org.localmc.tools.ftbqkeys.api;

import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.task.Task;

import java.util.List;

public interface QuestExtension {
    List<Task> getTasks();
    List<Reward> getRewards();
}
