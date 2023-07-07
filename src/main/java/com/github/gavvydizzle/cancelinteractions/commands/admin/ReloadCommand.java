package com.github.gavvydizzle.cancelinteractions.commands.admin;

import com.github.gavvydizzle.cancelinteractions.CancelInteractions;
import com.github.gavvydizzle.cancelinteractions.InventoryManager;
import com.github.gavvydizzle.cancelinteractions.commands.AdminCommandManager;
import com.github.mittenmc.serverutils.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends SubCommand {

    private final InventoryManager inventoryManager;

    public ReloadCommand(AdminCommandManager commandManager, InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;

        setName("reload");
        setDescription("Reload this plugin");
        setSyntax("/" + commandManager.getCommandDisplayName() + " reload");
        setColoredSyntax(ChatColor.YELLOW + getSyntax());
        setPermission(commandManager.getPermissionPrefix() + getName().toLowerCase());
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        try {
            CancelInteractions.getInstance().reloadConfig();
            inventoryManager.reload(true);
            sender.sendMessage(ChatColor.GREEN + "[CancelInteractions] Successfully reloaded");
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "[CancelInteractions] Encountered an error when reloading. Check the console");
        }
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}