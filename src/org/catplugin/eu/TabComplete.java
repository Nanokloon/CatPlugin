package org.catplugin.eu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {

    private static final String[] COMMANDS = {"chickensword","cannon","netherapple","heartychest","heartyhelmet","heartylegs","heartyboots"};

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        final List<String> completions = new ArrayList<>();
        //copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
        org.bukkit.util.StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        //sort the list
        Collections.sort(completions);
        return completions;
    }
}
