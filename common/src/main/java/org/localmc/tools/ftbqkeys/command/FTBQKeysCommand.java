package org.localmc.tools.ftbqkeys.command;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbquests.quest.*;
import dev.ftb.mods.ftbquests.quest.loot.RewardTable;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.task.Task;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.apache.commons.io.FileUtils;
import org.localmc.tools.ftbqkeys.FTBQKeysMod;
import org.localmc.tools.ftbqkeys.mixin.BaseQuestFileAccessor;
import org.localmc.tools.ftbqkeys.mixin.ChapterImageMixin;

import java.io.File;
import java.util.List;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FTBQKeysCommand {
    public static void serverRegisterCommandsEvent(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        RootCommandNode<CommandSourceStack> rootCommandNode = commandDispatcher.getRoot();
        LiteralCommandNode<CommandSourceStack> commandNode = Commands.literal("ftbqkey").executes(context -> 0).build();

        ArgumentCommandNode<CommandSourceStack, String> argumentCommandNode = Commands.argument("lang", StringArgumentType.word()).executes(Ctx -> {
            try {
                File parent = new File(FTBQKeysMod.gameDir.toFile(), "ftbqkeys");
                File transFiles = new File(parent, "kubejs/assets/kubejs/lang/");
                File questsFolder = new File(FTBQKeysMod.configDir.toFile(), "ftbquests/");

                if (questsFolder.exists()) {
                    File backup = new File(parent, "backup/ftbquests");
                    FileUtils.copyDirectory(questsFolder, backup);
                }

                TreeMap<String, String> transKeys = new TreeMap<>();
                BaseQuestFile file = FTBQuestsAPI.api().getQuestFile(false);

                for (int i = 0; i < file.getRewardTables().size(); i++) {
                    RewardTable table = file.getRewardTables().get(i);

                    transKeys.put("loot_table." + (i + 1), table.getRawTitle());
                    table.getRawTitle().compareTo("{" + "loot_table." + (i + 1) + "}");
                }

                for (int i = 0; i < ((BaseQuestFileAccessor) file).getChapterGroups().size(); i++) {
                    ChapterGroup chapterGroup = ((BaseQuestFileAccessor) file).getChapterGroups().get(i);

                    if (!chapterGroup.getRawTitle().isBlank()) {
                        transKeys.put("category." + (i + 1), chapterGroup.getRawTitle());
                        chapterGroup.getRawTitle().compareTo("{" + "category." + (i + 1) + "}");
                    }
                }

                for (int i = 0; i < file.getAllChapters().size(); i++) {
                    Chapter chapter = file.getAllChapters().get(i);

                    String prefix = "chapter." + (i + 1);

                    if (!chapter.getRawTitle().isBlank()) {
                        transKeys.put(prefix + ".title", chapter.getRawTitle());
                        chapter.getRawTitle().compareTo("{" + prefix + ".title" + "}");
                    }

                    if (chapter.getRawSubtitle().size() > 0) {
                        transKeys.put(prefix + ".subtitle", String.join("\n", chapter.getRawTitle()));
                        chapter.getRawSubtitle().clear();
                        chapter.getRawSubtitle().add("{" + prefix + ".subtitle" + "}");
                    }


                    for (int i1 = 0; i1 < chapter.images().toList().size(); i1++) {
                        ChapterImage chapterImage = chapter.images().toList().get(i1);
                        List<String> hover = new ChapterImageMixin().getHovers();

                        if (!hover.isEmpty()) {
                            transKeys.put(prefix + ".image." + (i1 + 1), String.join("\n", hover));
                            hover.clear();
                            hover.add("{" + prefix + ".image." + (i1 + 1) + "}");
                        }
                    }

                    for (int i1 = 0; i1 < chapter.getQuests().size(); i1++) {
                        Quest quest = chapter.getQuests().get(i1);

                        if (!quest.getRawTitle().isBlank()) {
                            transKeys.put(prefix + ".quest." + (i1 + 1) + ".title", quest.getRawTitle());
                            quest.getRawTitle().compareTo("{" + prefix + ".quest." + (i1 + 1) + ".title}");
                        }

                        if (!quest.getRawSubtitle().isBlank()) {
                            transKeys.put(prefix + ".quest." + (i1 + 1) + ".subtitle", quest.getRawTitle());
                            quest.getRawSubtitle().compareTo("{" + prefix + ".quest." + (i1 + 1) + ".subtitle" + "}");
                        }

                        if (quest.getRawDescription().size() > 0) {
                            List<String> descList = Lists.newArrayList();

                            StringJoiner joiner = new StringJoiner("\n");
                            int num = 1;

                            for (int i2 = 0; i2 < quest.getRawDescription().size(); i2++) {
                                String desc = quest.getRawDescription().get(i2);

                                final String regex = "\\{image:.*?}";

                                if (desc.contains("{image:")) {
                                    if (!joiner.toString().isBlank()) {
                                        transKeys.put(prefix + ".quest." + (i1 + 1) + ".description." + num, joiner.toString());
                                        descList.add("{" + prefix + ".quest." + (i1 + 1) + ".description." + num + "}");
                                        joiner = new StringJoiner("\n");
                                        num++;
                                    }

                                    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                                    final Matcher matcher = pattern.matcher(desc);

                                    while (matcher.find()) {
                                        desc = desc.replace(matcher.group(0), "");
                                        descList.add(matcher.group(0));
                                    }
                                } else {
                                    if (desc.isBlank()) {
                                        joiner.add("\n");
                                    } else {
                                        joiner.add(desc);
                                    }
                                }
                            }

                            if (!joiner.toString().isBlank()) {
                                transKeys.put(prefix + ".quest." + (i1 + 1) + ".description." + num, joiner.toString());
                                descList.add("{" + prefix + ".quest." + (i1 + 1) + ".description." + num + "}");
                            }

                            quest.getRawDescription().clear();
                            quest.getRawDescription().addAll(descList);
                        }

                        for (int i2 = 0; i2 < quest.getTasks().size(); i2++) {
                            Task task = quest.getQuestFile().getTask(i2);

                            if (!task.getRawTitle().isBlank()) {
                                transKeys.put(prefix + ".quest." + (i1 + 1) + ".task." + (i2 + 1) + ".title", task.getRawTitle());
                                task.getRawTitle().compareTo("{" + prefix + ".quest." + (i1 + 1) + ".task." + (i2 + 1) + ".title}");
                            }
                        }

                        for (int i2 = 0; i2 < quest.getRewards().size(); i2++) {
                            Reward reward = quest.getQuestFile().getReward(i2);

                            if (!reward.getRawTitle().isBlank()) {
                                transKeys.put(prefix + ".quest." + (i1 + 1) + ".reward." + (i2 + 1) + ".title", reward.getRawTitle());
                                reward.getRawTitle().compareTo("{" + prefix + ".quest." + (i1 + 1) + ".reward." + (i2 + 1) + ".title}");
                            }
                        }
                    }
                }

                File output = new File(parent, "config/ftbquests");

                file.writeDataFull(output.toPath());

                String lang = Ctx.getArgument("lang", String.class);
                FTBQKeysMod.saveLang(transKeys, lang, transFiles);

                if (!lang.equalsIgnoreCase("en_us")) {
                    FTBQKeysMod.saveLang(transKeys, "en_us", transFiles);
                }

                Ctx.getSource().getPlayerOrException().sendSystemMessage(Component.translatable("command.ftbqkeys.message" + parent.getAbsolutePath()));

            } catch (Exception e) {
                e.printStackTrace();
            }

            return 1;
        }).build();

        rootCommandNode.addChild(commandNode);
        commandNode.addChild(argumentCommandNode);
    }
}
