package org.localmc.tools.ftbqkeys.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import org.localmc.tools.ftbqkeys.FTBQKeysUtil;

import java.util.stream.Collectors;

public class FTBQKeysCommands {
    public static void registerCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, Commands.CommandSelection commandSelection) {
        RootCommandNode<CommandSourceStack> rootCommandNode = commandDispatcher.getRoot();
        LiteralCommandNode<CommandSourceStack> commandNode = Commands.literal("ftbqkey").executes(context -> 0).build();

        ArgumentCommandNode<CommandSourceStack, String> argumentCommandNode = Commands.argument("lang", StringArgumentType.word()).suggests((C1, c2) -> SharedSuggestionProvider.suggest(Minecraft.getInstance().getLanguageManager().getLanguages().stream().map(LanguageInfo::getCode).collect(Collectors.toList()).toArray(new String[0]), c2)).executes(Ctx -> {
            FTBQKeysUtil.exportedLangFile(Ctx);
            return 1;
        }).build();

        rootCommandNode.addChild(commandNode);
        commandNode.addChild(argumentCommandNode);
    }
}
