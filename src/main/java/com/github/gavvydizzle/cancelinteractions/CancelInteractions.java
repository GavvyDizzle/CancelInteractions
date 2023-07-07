package com.github.gavvydizzle.cancelinteractions;

import com.github.gavvydizzle.cancelinteractions.commands.AdminCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CancelInteractions extends JavaPlugin {

    private static CancelInteractions instance;

    @Override
    public void onEnable() {
        instance = this;

        InventoryManager inventoryManager = new InventoryManager(instance);
        getServer().getPluginManager().registerEvents(inventoryManager, this);

        new AdminCommandManager(getCommand("cancelinteractions"), inventoryManager);
    }

    public static CancelInteractions getInstance() {
        return instance;
    }
}
