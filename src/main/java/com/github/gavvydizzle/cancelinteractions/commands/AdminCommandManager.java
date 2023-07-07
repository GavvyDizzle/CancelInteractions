package com.github.gavvydizzle.cancelinteractions.commands;

import com.github.gavvydizzle.cancelinteractions.InventoryManager;
import com.github.gavvydizzle.cancelinteractions.commands.admin.HelpCommand;
import com.github.gavvydizzle.cancelinteractions.commands.admin.ReloadCommand;
import com.github.mittenmc.serverutils.CommandManager;
import org.bukkit.command.PluginCommand;

public class AdminCommandManager extends CommandManager {

    public AdminCommandManager(PluginCommand command, InventoryManager inventoryManager) {
        super(command);

        registerCommand(new HelpCommand(this));
        registerCommand(new ReloadCommand(this, inventoryManager));
        sortCommands();
    }
}