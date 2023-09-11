package org.localmc.tools.ftbqkeys.mixin;

import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.task.Task;
import org.localmc.tools.ftbqkeys.api.QuestExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Quest.class)
public class QuestMixin implements QuestExtension {

    @Shadow
    @Final
    private List<Task> tasks;

    @Shadow
    @Final
    private List<Reward> rewards;

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public List<Reward> getRewards() {
        return rewards;
    }
}
